import java.io.IOException;
import java.io.File;
import java.nio.channels.FileChannel;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	private static Random random = new Random();

	public static void main(String[] args) throws IOException {
	if (args.length != 3) {
		System.out.println("인자가 3개 필요합니다.");
		System.out.println("[1] 접속할 주소");
		System.out.println("[2] 접속한 포트번호");
		System.out.println("[3] 전송할 파일 이름");
	} // if
	String ipAddress = args[0];
	int portNumber = Integer.parseInt(args[1]);
	String fileName = args[2];

	InetSocketAddress serverAddress = new InetSocketAddress(ipAddress, portNumber);
	SocketChannel channel = SocketChannel.open();

	channel.connect(serverAddress);

	if (channel.isConnected()) {
		System.out.println("접속 성공");
		System.out.println(fileName + "\t전송시작");
		channel.write(ByteBuffer.wrap(fileName.getBytes()));
		int finishCode = random.nextInt(Integer.MAX_VALUE);
		String finishString = Integer.toString(finishCode);
		channel.write(ByteBuffer.wrap(finishString.getBytes()));
		File fileToSend = new File(fileName);
		FileChannel fileChannel = FileChannel.open(fileToSend.toPath());
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while(fileChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			System.out.println(byteBuffer);
			channel.write(byteBuffer);
			byteBuffer.clear();
		}
		System.out.println("finishString Send");
		channel.write(ByteBuffer.wrap(finishString.getBytes()));
		channel.write(ByteBuffer.wrap(finishString.getBytes()));
		channel.write(ByteBuffer.wrap(finishString.getBytes()));
		fileChannel.close();
	} // if

	channel.close();
	} // void main()
} // class Main
