import java.util.List;
import java.util.LinkedList;

public class Post implements SnsContent, Comparable<Post> {
	private long id;
	private long creatorid;
	private long like;
	private List<Comment> clist;

	public Post(long id, long creatorid) {
		this.id = id;
		this.creatorid = creatorid;
		this.like = 0;
		this.clist = new LinkedList<Comment>();
	} // End Post

	public Post(long id, long creatorid, long like) {
		this.id = id;
		this.creatorid = creatorid;
		this.like = like;
		this.clist = new LinkedList<Comment>();
	} // End Post

	public long getId() {
		return this.id;
	} // End long getId()

	public long getCreatorId() {
		return this.creatorid;
	} // End long getCreatorId()

	public long getLike() {
		return this.like;
	} // End long getLike()

	public long setLike(long like) {
		this.like = like;
		return this.like;
	} // End long setLike()


	public boolean addComment(Comment cm) {
		return clist.add(cm);
	} // End addComment()

	public List<Comment> getCommentList() {
		return clist;
	} // End List<Comment> getCommentList

	@Override
	public boolean equals(Object o) {
		if( !(o instanceof Post) ) {
			return false;
		}

		return (this.id == ((Post)o).id) && 
				(this.creatorid == ((Post)o).creatorid);
	} // End equals()

	@Override
	public int hashCode() {
		return (int) (id * creatorid);
	} // End hashCode()

	public int compareTo(Post post) {
		long iddiff = this.id - post.id;
		if(iddiff > 0) {
			return 1;
		} else if(iddiff < 0) {
			return -1;
		}

		long ciddiff = this.creatorid - post.creatorid;
		if(ciddiff > 0) {
			return 1;
		} else if(ciddiff < 0) {
			return -1;
		}

		return 0;
	}

	public String toString() {
		return "Post Id: " + Long.toString(id) + 
				", CreatorId: " + Long.toString(creatorid) + 
				", Like: " + Long.toString(like) +
				", Comment: " + Integer.toString(clist.size());
	} // End toString()

} // End class Post
