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
	/**
	 * 종료를 위한 난수 생성용 변수
	 */
	private static Random random = new Random();
	/**
	 * 메인
	 */
	public static void main(String[] args) throws IOException {
		/**
		 * 인자 개수 확인
		 * 인자1: 주소
		 * 인자2: 포트
		 * 인자3: 파일이름
		 */
		if (args.length != 3) {
			System.out.println("인자가 3개 필요합니다.");
			System.out.println("[1] 접속할 주소");
			System.out.println("[2] 접속한 포트번호");
			System.out.println("[3] 전송할 파일 이름");
		} // if
		String ipAddress = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String fileName = args[2];
		/**
		 * TCP SocketChannel 생성
		 * 인자로 넣어준 주소, 포트로 연결
		 * 이때 연결이 완료됨
		 * Non-Blocking 설정을 하지 않았으므로 연결이 될 때 까지 대기
		 */
		InetSocketAddress serverAddress = new InetSocketAddress(ipAddress, portNumber);
		SocketChannel channel = SocketChannel.open();
		channel.connect(serverAddress);
		System.out.println("접속 성공");
		/**
		 * 만일을 위해 확인
		 * 연결이 되어있지 않을 수도 있기 때문
		 */
		if (channel.isConnected()) { /** 연결이 되어 있다면 */
			/**
			 * 파일 이름 전송
			 */
			channel.write(ByteBuffer.wrap(fileName.getBytes()));
			/**
			 * 종료용 난수 전송
			 */
			int finishCode = random.nextInt(Integer.MAX_VALUE);
			String finishString = Integer.toString(finishCode);
			channel.write(ByteBuffer.wrap(finishString.getBytes()));
			/**
			 * 전송할 파일 열기
			 */
			File fileToSend = new File(fileName);
			FileChannel fileChannel = FileChannel.open(fileToSend.toPath());
			/**
			 * SocketChannel을 통해 전송하기 위한 변수(ByteBuffer)
			 */
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			/**
			 * 1024 Bytes씩 읽어와서 전송
			 */
			System.out.println(fileName + "\t전송시작");
			while(fileChannel.read(byteBuffer) > 0) { /** 1024 Bytes 읽기 */
				byteBuffer.flip(); /** 쓰기 - 읽기 모드 변경 */
				System.out.println(byteBuffer); /** 디버그용 ByteBuffer 상태 출력 */
				channel.write(byteBuffer); /** TCP 채널로 전송 */
				byteBuffer.clear(); /** 전송용 변수 클리어 */
			}
			/**
			 * 파일 전송 종료
			 */
			System.out.println("finishString Send");
			channel.write(ByteBuffer.wrap(finishString.getBytes()));
			channel.write(ByteBuffer.wrap(finishString.getBytes()));
			channel.write(ByteBuffer.wrap(finishString.getBytes()));
			fileChannel.close();
		} // if
		/**
		 * 소켓채널 닫기
		 */
		channel.close();
	} // void main()
} // class Main
