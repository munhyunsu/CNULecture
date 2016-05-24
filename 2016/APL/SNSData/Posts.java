import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.Iterator;

public class Posts {
	public static List<Post> m0(Collection<Post> cpost, 
			Comparator<Post> strategy) {
		List<Post> l = new LinkedList<Post>(cpost);

		Collections.sort(l, strategy);

		return l;
	} // End m0()


//	public static List<Post> m00(Collection<Post> cpost, IntSupplier strategy) {
//		List<Post> l = new LinkedList<Post>(cpost);
//
//		Collections.sort(l, strategy.apply());
//
//		return l;
//} // End m00()

	public static List<Post> m1(Collection<Post> cpost) {
		List<Post> l = m0(cpost, new Comparator<Post>() {
				public int compare(Post p1, Post p2) {
					long likediff = p1.getLike() - p2.getLike();
					if(likediff < 0) {
						return 1;
					} else if (likediff > 0) {
						return -1;
					}

					//return (p1.getLike()).compareTo(p2.getLike());
					return 0;
				}
		});

		return l;
	} // End m1()

	public static List<Post> m11(Collection<Post> cpost) {
		List<Post> l = new LinkedList<Post>(cpost);

		//Collections.sort(l, (Post p1, Post p2) -> (-1) * Long.valueOf(p1.getLike()).compareTo(Long.valueOf(p2.getLike())));
	 	l = m0(cpost, (Post p1, Post p2) -> (-1)*Long.valueOf(p1.getLike()).compareTo(Long.valueOf(p2.getLike())));

		return l;
	}



	public static List<Post> m2(Collection<Post> cpost) {
		List<Post> l = m0(cpost, new Comparator<Post>() {
				public int compare(Post p1, Post p2) {
					long score_p1 = p1.getLike();
					long score_p2 = p2.getLike();
					Iterator it = null;

					it = (p1.getCommentList()).iterator();
					while(it.hasNext()) {
						score_p1 = score_p1 + ((Comment)(it.next())).getLike();
					}
					it = (p2.getCommentList()).iterator();
					while(it.hasNext()) {
						score_p2 = score_p2 + ((Comment)(it.next())).getLike();
					}
					
					long likediff = score_p1 - score_p2;
					if(likediff < 0) {
						return 1;
					} else if (likediff > 0) {
						return -1;
					}

					//return (p1.getLike()).compareTo(p2.getLike());
					return 0;
				}
		});

		return l;
	} // End m2()

} // End class Posts
