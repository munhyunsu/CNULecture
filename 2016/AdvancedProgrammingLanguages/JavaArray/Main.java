/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) {
		Object[] objectArray = new Integer[10];

		for (int i = 0; i < objectArray.length; i++) {
			objectArray[i] = new Integer(i);
		} // end for

		for (Object object : objectArray) {
			System.out.println(object);
		} // end for

		/**
		 * 여기서 Run-time Error
		 */
		objectArray[4] = new String("This is Run-time Error");
	} // void main()
} // class Main
