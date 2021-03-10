package model;
import java.util.Arrays;
import java.util.Date;

public class Book 
{

	private Long bookId;
	private String title;
	private String authorName;
	private Date publishedDtae;
	private String country;
	private byte[] picture;
	
	public Book() {}

	public Book(Long bookId, String title, String authorName, Date publishedDtae, String country, byte[] picture) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authorName = authorName;
		this.publishedDtae = publishedDtae;
		this.country = country;
		this.picture = picture;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getPublishedDtae() {
		return publishedDtae;
	}

	public void setPublishedDtae(Date publishedDtae) {
		this.publishedDtae = publishedDtae;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", authorName=" + authorName + ", publishedDate="
				+ publishedDtae + ", country=" + country + ", picture=" + picture.length+ "]";
	}
	
}
