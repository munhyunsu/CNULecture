package org.cscnu.hobby;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) {
	    File file = new File("pg1342.txt");
        FileInputStream fileInputSteam;

        try {
            fileInputSteam = new FileInputStream(file);
            FileChannel fileChannel = fileInputSteam.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1500);

            int readBytes = 0;
            readBytes = fileChannel.read(byteBuffer);

            System.out.println("ReadBytes: \t" + Integer.toString(readBytes));

            if(readBytes > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.print(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
