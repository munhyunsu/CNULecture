import java.util.Vector;
import java.util.EmptyStackException;


public class UStack<T> {
	private Vector<T> v = null;

	public UStack() {
		v = new Vector<T>();
	}

	public T push(T item) {
		v.addElement(item);
		
		return item;
	}

	public T pop() {
		T obj;
		int length = v.size();

		obj = this.peak();
		v.removeElementAt(length - 1);

		return obj;
	}
		
	public T peak() {
		int length = v.size();

		if(length == 0) {
			throw new EmptyStackException();
		}

		return v.elementAt(length - 1);
	}

	public boolean empty() {
		return v.size() == 0;
	}

	public int search(Object o) {
		int idx = v.lastIndexOf(o);

		if(idx >= 0) {
			return v.size() - idx;
		}

		return -1;
	}
} // End class Stack
