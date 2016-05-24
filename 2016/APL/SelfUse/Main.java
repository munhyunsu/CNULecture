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
		System.out.println("This is Self-Used Usage Problem");

		SayHello sh = new SayHello();
		sh.hello();

		SayNice sn = new SayNice();
		sn.hello();


		return;
	} // End void main()
} // End class Main

