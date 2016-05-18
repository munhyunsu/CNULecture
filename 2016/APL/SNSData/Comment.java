import java.util.List;
import java.util.LinkedList;

public class Comment implements SnsContent, Comparable<Comment>{
	private long id;
	private long creatorid;
	private long like;
	private Post hpost;
   
	public Comment(long id, long creatorid, Post hpost) {
		this.id = id;
		this.creatorid = creatorid;
		this.like = 0;
		this.hpost = hpost;
	} // End Comment

	public Comment(long id, long creatorid, long like, Post hpost) {
		this.id = id;
		this.creatorid = creatorid;
		this.like = like;
		this.hpost = hpost;
	} // End Comment

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

	public Post getHostingPost() {
		return hpost;
	} // End List<Post> getHostingPost

	@Override
	public boolean equals(Object o) {
		if( !(o instanceof Comment) ) {
			return false;
		}

		return (this.id == ((Comment)o).id) && 
				(this.creatorid == ((Comment)o).creatorid);
	} // End equals()

	@Override
	public int hashCode() {
		return (int) (id * creatorid);
	} // End hashCode()

	public int compareTo(Comment comment) {
		long iddiff = this.id - comment.id;
		if(iddiff > 0) {
			return 1;
		} else if(iddiff < 0) {
			return -1;
		}

		long ciddiff = this.creatorid - comment.creatorid;
		if(ciddiff > 0) {
			return 1;
		} else if(ciddiff < 0) {
			return -1;
		}

		return 0;
	}

	public String toString() {
		return "Comment Id: " + Long.toString(id) + 
				", CreatorId: " + Long.toString(creatorid) + 
				", Like: " + Long.toString(like);
	} // End toString()

} // End class Comment
