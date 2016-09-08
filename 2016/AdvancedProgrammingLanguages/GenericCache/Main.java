

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) {
		for (Integer integer : IntegerCache.cache) {
			System.out.println(integer);
		}

		for (Object object : Cache.cache) {
			System.out.println(object);
		}
	} // void main()


	private static class Cache {
		private Cache() {
		} // Cache
		static final Object[] cache = new Object[-(-128) + 128 + 1];
		static {
			for (int i = 0; i < cache.length; i++) {
				cache[i] = new Integer(i - 128);
			} 
			cache[0] = new String("First");
			cache[cache.length-1] = new String("Last");
		}
	} // class Cache

	private static class IntegerCache {
		private IntegerCache() {
		} // IntegerCache()
		static final Integer cache[] = new Integer[-(-128) + 128 + 1];
		static {
			for (int i = 0; i < cache.length; i++) {
				cache[i] = new Integer(i - 128);
			}
		}
	} // class IntegerCache
} // class Main
