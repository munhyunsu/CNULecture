package kty.onos.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

/**
 * 이 소스는 ONOS 1.5.1 의  Restful을 호출하여 원격으로 로컬에 빌드된 ONOS App을 배포 관리할 수 있는 기능을 구현했습니다.
 * ONOS의 onos-app 쉡스크리트의 내용을 참조해서 만들었습니다.
 * 소스는 ONOS 기반 시스템을 개발하는 누구나 자유롭게 복사/수정해서 사용할 수 있습니다.
 * 사용하실 때 저를 기억해 주시면 더 좋습니다. 이메일로 안부 정도 보내주셔도 좋습니다. ^^
 * 
 * @author KIM TAEYOUNG	kty@sntsoft.co.kr
 * @since	2016.05
 * @version	1.5.1
 *
 */
public class OnosApp {
  private static String auth;
  
  private static File getTargetOarFile(String filePath){
    return new File(filePath);
  }
  private static void printUsage(){
    System.out.println("Usage : java kty.onos.tools.OnosApp [user:pwd@host] [list|uninstall|install|install!|reInstall|reInstall!|activate|deactivate] [oarFile|appName] [appName]");
    System.out.println("user:pwd - default=onos:rocks");
    System.out.println("list - no params");
    System.out.println("install|install! - [oarFile]");
    System.out.println("reInstall|reInstall! - [oarFile] [appName]");
    System.out.println("uninstall|activate|deactivate - [appName]");
    System.out.println("appName - pom.xml <onos.app.name>[appName]</onos.app.name>");
  }
  public static void main(String[] args) throws Exception{
    if( args.length == 0 ){
      printUsage();
      return;
    }
    
    String user = null;
    String pwd = null;
    String host = null;
    String command = null;
    String appName = null;
    File targetOarFile = null;
    try{
      host = args[0].trim();
      int idx = host.indexOf(":");
      user = host.substring(0, idx).trim();
      int jdx = host.indexOf("@", idx);
      pwd = host.substring(idx+1,jdx).trim();
      host = host.substring(jdx+1).trim();
      auth = DatatypeConverter.printBase64Binary((user+":"+pwd).getBytes());
      
      command = args[1].trim();
      if( command.equals("install") || command.equals("install!") ){
        targetOarFile = getTargetOarFile(args[2].trim());
      }else if( command.equals("reInstall") || command.equals("reInstall!") ){
        targetOarFile = getTargetOarFile(args[2].trim());
        appName = args[3].trim();
      }else if( command.equals("uninstall") || command.equals("activate") || command.equals("deactivate") ){
        appName = args[2].trim();
      }
    }catch(Exception e){
      System.out.println("Error " + e.getClass().getName() + ":" + e.getMessage());
      printUsage();
      return;
    }
    if( command.equals("list") ){
      list(host);
    }else if( command.equals("uninstall") ){
      uninstall(host, appName);
    }else if( command.equals("install") ){
      install(host, targetOarFile, false);
    }else if( command.equals("install!") ){
      install(host, targetOarFile, true);
    }else if( command.equals("reInstall") ){
      reInstall(host, appName, targetOarFile, false);
    }else if( command.equals("reInstall!") ){
      reInstall(host, appName, targetOarFile, true);
    }else if( command.equals("activate") ){
      activate(host, appName);
    }else if( command.equals("deactivate") ){
      deactivate(host, appName);
    }
  }
  public static void install(String host, File targetOarFile, boolean activate) throws Exception{
    System.out.println("try install " + targetOarFile.getName());
    
    URL url = new URL("http://"+host+":8181/onos/v1/applications"+(activate?"?activate=true":""));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", "Basic " + auth);
    con.setRequestProperty("Content-type", "application/octet-stream");
    con.setRequestProperty("Content-length", String.valueOf(targetOarFile.length()));
    con.setRequestMethod("POST");
    con.setUseCaches(false);
    con.setDoOutput(true);
    con.setDoInput(true);
    byte[] b = new byte[1024];
    int rb = 0;
    FileInputStream fis = new FileInputStream(targetOarFile);
    OutputStream out = con.getOutputStream();
    while( (rb=fis.read(b, 0, b.length)) != -1 ){
      out.write(b, 0, rb);
    }
    fis.close();
    out.flush();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String temp;
    while((temp = in.readLine()) != null) System.out.println(temp);
    out.close();
    in.close();
    con.disconnect();
    
    System.out.println("succeed install " + targetOarFile.getName() + " activate:"+activate);
  }
  public static void uninstall(String host, String appName) throws Exception{
    System.out.println("try uninstall " + appName);
    
    URL url = new URL("http://"+host+":8181/onos/v1/applications/"+appName);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", "Basic " + auth);
    con.setRequestProperty("Content-type", "application/octet-stream");
    con.setRequestMethod("DELETE");
    con.setUseCaches(false);
    con.setDoInput(true);
    con.connect();
    try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); ){
      String temp;
      while((temp = in.readLine()) != null) System.out.println(temp);
    }
    con.disconnect();
    
    System.out.println("succeed uninstall " + appName);
  }
  public static void reInstall(String host, String appName, File targetOarFile, boolean activate) throws Exception{
    System.out.println("try reInstall " + appName);
    
    uninstall(host, appName);
    
    install(host, targetOarFile, true);
    
    System.out.println("succeed reInstall " + appName + " activate:"+activate);
  }
  public static void activate(String host, String appName) throws Exception{
    System.out.println("try activate " + appName);
    
    URL url = new URL("http://"+host+":8181/onos/v1/applications/"+appName+"/active");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", "Basic " + auth);
    con.setRequestMethod("POST");
    con.setUseCaches(false);
    con.setDoInput(true);
    con.connect();
    try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); ){
      String temp;
      while((temp = in.readLine()) != null) System.out.println(temp);
    }
    con.disconnect();
    
    System.out.println("succeed activate " + appName);
  }
  public static void deactivate(String host, String appName) throws Exception{
    System.out.println("try deactivate " + appName);
    
    URL url = new URL("http://"+host+":8181/onos/v1/applications/"+appName+"/active");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", "Basic " + auth);
    con.setRequestMethod("DELETE");
    con.setUseCaches(false);
    con.setDoInput(true);
    con.connect();
    try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); ){
      String temp;
      while((temp = in.readLine()) != null) System.out.println(temp);
    }
    con.disconnect();
    
    System.out.println("succeed deactivate " + appName);
  }
  public static void list(String host) throws Exception{
    URL url = new URL("http://"+host+":8181/onos/v1/applications");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", "Basic " + auth);
    con.setRequestProperty("Content-type", "application/octet-stream");
    con.setUseCaches(false);
    con.setDoInput(true);
    con.setRequestMethod("GET");
    con.connect();
    try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); ){
      String temp;
      while((temp = in.readLine()) != null) System.out.println(temp);
    }
    con.disconnect();
  }
}
