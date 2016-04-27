class A {
	void f() {
		System.out.println("A.f");
	}
	void g() {
		f();
	}
}

class B extends A {
	void f() {
		System.out.println("B.f");
	}
	void g() {
		super.g();
	}

	public static void main(String[] args) {
		(new B()).f();
		(new B()).g();
	}
}
