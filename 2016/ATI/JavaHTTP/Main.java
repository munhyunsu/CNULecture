import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.channels.Selector;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * 
		 */
		if(args.length != 2) {
			System.out.println("인자가 2개 필요합니다.");
			System.out.println("[1] 접속할 URL");
			System.out.println("[2] 저장할 파일 이름");
			System.exit(0);
		}
			
		/**
		 * 접속할 URL 
		 */
		URL url = new URL(args[0]);
		/**
		 * URL의 IP주소 얻기
		 */
		String host = url.getHost();
		/**
		 * 포트 번호
		 */
		int port = 80;
		/**
		 * 인덱스 페이지
		 */
		String file = "/";

		/**
		 * 소켓 채널을 연다.
		 */
		SocketChannel channel = SocketChannel.open();
		/**
		 * 논블로킹 모드로 설정
		 */
		channel.configureBlocking(false);
		/**
		 * 가능한 모드 출력
		 */ 
		System.out.println("가능한 모드: " + Integer.toString(channel.validOps()));
		/**
		 * 서버에 연결
		 */
		channel.connect(new InetSocketAddress(host, port));

		/**
		 * (Nonblocking 모드) 연결 마무리
		 * 셀렉터 생성 및 소켓 채널 등록
		 */
		Selector slt = Selector.open();
		if(channel.finishConnect() == true) {
			System.out.println("연결 완료");
			channel.register(slt, SelectionKey.OP_WRITE );
		} else {
			System.out.println("연결 미완료");
			//System.out.println("finishConnect(): " + Boolean.toString(channel.finishConnect()));
	//		channel.configureBlocking(false);
			channel.register(slt, SelectionKey.OP_CONNECT );
		}

// (블로킹모드) DEBUG 용
//		try {
//			Thread.sleep(1000);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		channel.configureBlocking(false);
//		try {
//			Thread.sleep(1000);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}

		/**
		 * 예외처리를 잡아야하는가?
		 * Thread.sleep과 ctl + c를 위해서 일단 유지
		 */
		try {
			/**
			 * 파일을 모두 다 읽을 때 까지 while
			 * 혹은 채널이 모두 사라질 때 까지 while
			 */
			while(true) {
				/**
				 * 활성화 된 채널 선택
				 * selectNow(): 논 블로킹 메서드
				 * select(): 블로킹 메서드 - select()에서 출력될 수 있는 채널이 0개일 경우 계속 대기하게 됨
				 */
				int readyChannel = slt.selectNow();
				System.out.println("이벤트가 뜬 채널: " + Integer.toString(readyChannel));
				/**
				 * 이벤트가 뜬 채널이 0개일 경우 
				 */
				if(readyChannel == 0) {
					/**
					 * 이벤트 채널이 0개이면 잠을 잔다
					 * 쿨쿨 잔다
					 */
					System.out.println("Sleep 100");
					Thread.sleep(100);
					/**
					 * 만일 키셋의 전체 크기가 0개일 경우 while문 탈출
					 */
					if((slt.keys()).size() == 0) {
						System.out.println("Key size 0");
						break;
					}
				}

				/**
				 * 이벤트가 뜬 채널을 순회하기 위한 '임시'변수
				 * LocalVPN에서 it.remove()하는 것은 채널을 '삭제하는 것이 아님'
				 */
				Iterator<SelectionKey> itr = (slt.selectedKeys()).iterator();
				/**
				 * 이벤트가 뜬 채널 순회
				 */
				while(itr.hasNext()) {
					/**
					 * 셀렉션키 세트 순회
					 * 키에 대한 정보 출력
					 * 추후 여러 키를 넣게 될 경우 구분하기 위함
					 */
					SelectionKey key = itr.next();
					System.out.println(key);

					/**
					 * isConnectable(): connect()가 불렸으나 아직 finishConnect()가 되지 않은 상태에 true
					 * finishConnect()가 된 후에는 false가 뜸
					 * 논 블로킹 모드에서 이 장소로 오게 됨
					 */
					if(key.isConnectable() == true) {
						System.out.println("isConnectable()");
						/**
						 * 연결 마무리: 이제부터 isConnectable()은 false가 뜬다
						 */
						System.out.println("finishConnect(): " + Boolean.toString(((SocketChannel)key.channel()).finishConnect()));
						/**
						 * 요청을 전송할 수 있는 모드를 대기하도록 변경
						 * OP_CONNECT와 OP_WRITE는 공존하지 않도록 할 것
						 * 구분이 힘들다고 한다(인터넷 상)
						 */
						key.interestOps(SelectionKey.OP_WRITE);
					} else {
						/**
						 * 디버그용 출력
						 */
						System.out.println("isNotConnectable()");
						System.out.println("finishConnect(): " + Boolean.toString(((SocketChannel)key.channel()).finishConnect()));
					}

					/**
					 * isReadable: NIO(NIC)를 통해 현재 채널에 데이터가 도착해 읽을 수 있는 상태에 true
					 * 데이터가 없을 경우 false가 나오는지는 확실하지 않음(조사 필요)
					 */
					if(key.isReadable() == true) {
						System.out.println("isReadable()");
						/**
						 * 요청한 결과(현 소스가 80포트로 HTTP 문서를 요청)를 파일로 저장
						 * 2번째 인자로 준 이름으로 저장
						 */
						FileOutputStream out = new FileOutputStream(args[1]);
						FileChannel saveFile = out.getChannel();
						/**
						 * Java.nio에서는 Byte단위의 Stream을 다루기 때문에 java.nio.ByteBuffer 클래스를 많이 사용함
						 * ByteBuffer 사용법에 익숙해질 필요가 있음
						 * ByteBuffer 할당
						 */
						ByteBuffer read_buffer = ByteBuffer.allocate(1024*10);
						/**
						 * 전송이 끝나 다 읽을 때 까지 Loop
						 */
						while(true) {
							/**
							 * while문 탈출을 위한 읽은 Byte 크기 확인용 변수
							 */
							int read_int = ((SocketChannel)key.channel()).read(read_buffer);
							System.out.println("Read From Channel: " + Integer.toString(read_int));
							/**
							 * 만일 읽은 Byte 크기가 -1일 경우 while 탈출
							 * sleep은 Buffer가 가득 찰 경우 제어가 되는지 확인하기 위함
							 */
							if(read_int == -1) {
								System.out.println("Read -1 Bytes");
								break;
							} else {
								Thread.sleep(100);
							}
							/**
							 * Buffer에 거꾸로 들어오기 때문에 flip()을 불러줘야 제대로 된 방향으로 정렬 됨
							 */
							read_buffer.flip();
							/**
							 * 파일에 작성
							 * Buffer의 정보(크기, 여유 공간 등이 나옴) 출력
							 */
							saveFile.write(read_buffer);
							System.out.println(read_buffer);
							/**
							 * Buffer 비우기
							 * 비우지 않을 경우 데이터 혼합: Error 발생(?)
							 */
							read_buffer.clear();
						}
						/**
						 * 전송이 끝난 후 채널 닫기
						 */
						System.out.println("Close Channels");
						saveFile.close();
						SocketChannel schannel = ((SocketChannel)key.channel());
						key.cancel();
						schannel.close();
						break;
					} else {
						/**
						 * 디버그용 출력
						 */
						System.out.println("isNotReadable()");
					}

					/**
					 * isWritable(): NIC(NIO)를 통해서 요청을 보낼 수 있을 때 true
					 */
					if(key.isWritable() == true) {
						System.out.println("isWritable()");
						/**
						 * 요청 제작
						 */
						String request = "GET " + file + 
								" HTTP/1.1\r\n" + 
								"User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36\r\n" + 
								"Accept: text/*\r\n" + 
								"Connection: close\r\n" + 
								"Host: " + host + 
								"\r\n\r\n";
						System.out.println("Request");
						/**
						 * ByteBuffer를 통해서 전송해야 함
						 * java.nio는 ByteBuffer 클래스를 활용
						 */
						ByteBuffer payload = ByteBuffer.wrap(request.getBytes("UTF-8"));
						((SocketChannel)key.channel()).write(payload);
						/**
						 * 요청을 전송한 후 Read에 이벤트가 발생하도록 변경
						 */
						key.interestOps(SelectionKey.OP_READ);
					} else {
						/**
						 * 디버그용 출력
						 */
						System.out.println("isNotWritable()");
					}
				} // 이벤트 뜬 채널 순회 while
			} // 전체 while 
		/**
		 * 예외 처리
		 * 특히 ctl + c
		 */
		} catch(Exception e) {
			/**
			 * 디버그용
			 * 예외처리 스택 출력
			 */
			System.out.println("Inturupted");
			e.printStackTrace();
			channel.close();
		} // try-catch
	} // void main()
} // class Main
