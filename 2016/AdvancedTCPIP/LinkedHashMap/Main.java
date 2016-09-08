
import java.io.IOException;

import java.util.Map;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		AbstractMap<String, Integer> map = new LinkedHashMap<String, Integer>(3, 1F, true) {
			private static final long serialVersionUID = 20160526154628L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
				return size() > 3;
			}
		};

		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.put("Four", 4);
		map.put("Five", 5);
		map.get("Three");
		map.put("Six", 6);

		map.get("One");

		System.out.println("After Input Key-Value");
		System.out.println(map);
		System.out.println("Map Size");
		System.out.println(map.size());



		map.clear();

		System.out.println("After Clear Key-Value");
		System.out.println(map);

	} // void main()
} // class Main
