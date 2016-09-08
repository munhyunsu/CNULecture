import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

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

		List plist = new LinkedList<Post>();

		Post ps = new Post(10, 100, 4);
		ps.addComment(new Comment(1, 100, 1, ps));
		ps.addComment(new Comment(2, 100, 1, ps));
		ps.addComment(new Comment(3, 100, 1, ps));
		ps.addComment(new Comment(4, 100, 1, ps));
		ps.addComment(new Comment(5, 100, 1, ps));
		plist.add(ps);
		System.out.println(ps);

		List clist = ps.getCommentList();
		Iterator it = clist.iterator();
		while(it.hasNext() == true) {
			System.out.println((Comment) it.next());
		}

		ps = new Post(11, 100, 3);
		ps.addComment(new Comment(6, 100, 3, ps));
		ps.addComment(new Comment(7, 100, 3, ps));
		ps.addComment(new Comment(8, 100, 3, ps));
		ps.addComment(new Comment(9, 100, 3, ps));
		ps.addComment(new Comment(10, 100, 3, ps));
		plist.add(ps);
		System.out.println(ps);
		
		ps = new Post(12, 100, 5);
		ps.addComment(new Comment(11, 100, 0, ps));
		ps.addComment(new Comment(12, 100, 0, ps));
		ps.addComment(new Comment(13, 100, 0, ps));
		ps.addComment(new Comment(14, 100, 0, ps));
		ps.addComment(new Comment(15, 100, 0, ps));
		plist.add(ps);
		System.out.println(ps);

		plist = Posts.m11(plist);
		it = plist.iterator();
		while(it.hasNext() == true) {
			System.out.println((Post) it.next());
		}

		plist = Posts.m2(plist);
		it = plist.iterator();
		while(it.hasNext() == true) {
			System.out.println((Post) it.next());
		}


		return;
	} // End void main()
} // End class Main

