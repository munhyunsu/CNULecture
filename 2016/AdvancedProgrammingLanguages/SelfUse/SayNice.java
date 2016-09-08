public class SayNice extends SayHello {
	@Override
	public void hello() {
		super.hello();
	} // End void hello

	@Override
	public void greet() {
		System.out.print("Nice to Meet, ");
	} // End void greet()

	@Override
	public void name() {
		System.out.println("Mun");
	} // End void name()

} // End class SayHello
