import java.lang.Integer;

public class Main {
	public static void main(String[] args) {
		/**
		* 테스트용 코드
		*/
		if(args.length > 0) {
			System.out.println("Hello, World!!" + args[0]);
		} else {
			System.out.println("Hello, World!");
		}

		UStack<Integer> st = new UStack<Integer>();

		st.push(new Integer(1));
		st.push(new Integer(2));

		System.out.println(st.pop());
		System.out.println(st.pop());

		return;
	} // End void main()
} // End class Main

