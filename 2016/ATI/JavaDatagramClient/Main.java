import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) throws IOException {
//		DatagramChannel client = null;
//		client = DatagramChannel.open();
//		client.bind(null);
//		String msg = "Hello";
//		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
//		InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8989);
//		client.send(buffer, serverAddress);
//		buffer.clear();
//		client.receive(buffer);
//		buffer.flip();
//		int limits = buffer.limit();
//		byte bytes[] = new byte[limits];
//		buffer.get(bytes, 0, limits);
//		String response = new String(bytes);
//		System.out.println("Server  responded: " + response);
//		client.close();

		/**
		 * 데이터 그램 실험
		 */
		/**
		 * 셀렉터 시작
		 */
		Selector selector = Selector.open();
		SelectionKey selectionKey = null;
		/**
		 * 데이터 그램 채널 오픈
		 */
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.configureBlocking(false);
		/**
		 * 데이터 그램 연결
		 * 이때 Packet 은 아무것도 움직이지 않음
		 */
		InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8989);
		datagramChannel.connect(serverAddress);
		/**
		 * 셀렉터에 등록
		 */
		selector.wakeup();
		selectionKey = datagramChannel.register(selector, SelectionKey.OP_READ);
		/**
		 * 메시지 전송
		 */
		String msg = "This is Datagram Example.";
		ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
		datagramChannel.send(byteBuffer, serverAddress);

		try {
			while (true) {
				int readyChannel = selector.selectNow();
				//int readyChannel = selector.select();
				System.out.println("이벤트가 뜬 채널: " + Integer.toString(readyChannel));
				if (readyChannel == 0) {
					System.out.println("Sleep 100");
					Thread.sleep(100);
					msg = "This is Datagram another Example.";
					byteBuffer = ByteBuffer.wrap(msg.getBytes());
					datagramChannel.send(byteBuffer, serverAddress);
					if ((selector.keys()).size() == 0) {
						System.out.println("Total Key Size: 0");
						break;
					}
					continue;
				} // end if readyChannel == 0
				Iterator<SelectionKey> itr = (selector.selectedKeys()).iterator();
				while (itr.hasNext()) {
					SelectionKey key = itr.next();
					itr.remove();
					System.out.println("상태");
					System.out.println("isConnectable(): " + Boolean.toString(key.isConnectable()));
					System.out.println("isReadable(): " + Boolean.toString(key.isReadable()));
					System.out.println("isWritable(): " + Boolean.toString(key.isWritable()));
					if (key.isReadable()) {
						byteBuffer.position(0);
						((DatagramChannel)key.channel()).read(byteBuffer);
						System.out.println(byteBuffer);
					} // end if key.isReadable
				} // end while itr.hasNext
			} // while(true)
		} catch (Exception e) {
			System.out.println("Interrupted");
			e.printStackTrace();
			datagramChannel.disconnect();
		} // try-catch 
	} // void main()
} // class Main
