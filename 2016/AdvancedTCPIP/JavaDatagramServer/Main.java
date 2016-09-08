import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		DatagramChannel server = null;
		server = DatagramChannel.open();
		InetSocketAddress sAddr = new InetSocketAddress("localhost", 8989);
		server.bind(sAddr);
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (true) {
			System.out.println("Waiting for a  message  from" + 
					"  a  remote  host at " + sAddr);
			SocketAddress remoteAddr = server.receive(buffer);
			buffer.flip();
			int limits = buffer.limit();
			byte bytes[] = new byte[limits];
			buffer.get(bytes, 0, limits);
			String msg = new String(bytes);

			System.out.println("Client at " + remoteAddr + "  says: " + msg);
			buffer.rewind();
			server.send(buffer, remoteAddr);
			buffer.clear();
		}
		//server.close();
	} // void main()
} // class Main
