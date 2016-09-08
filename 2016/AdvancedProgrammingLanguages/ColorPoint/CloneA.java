public class CloneA implements Cloneable {
	private Integer i;

	public CloneA(int a_i) {
		i = a_i;
	}

	public int GetI() {
		return i;
	}

	@Override
	public CloneA clone() {
		try {
			CloneA t = (CloneA)super.clone();
			t.i = t.i + 1;
			return t;
		} catch(CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
