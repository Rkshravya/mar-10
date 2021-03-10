package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookDaoImpl implements BookDao 
{
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
	SimpleDateFormat sdf2=new SimpleDateFormat("dd-MMM-yy");
	private Connection getConn() throws ClassNotFoundException, SQLException
	{
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String url, driver, username, password;
		driver=rb.getString("driver");
		url=rb.getString("url");
		username=rb.getString("username");
		password=rb.getString("password");
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	}
	@Override
	public int create(Book book)throws SQLException, ClassNotFoundException
	{
		System.out.println("Book object picture length:"+book.getPicture().length);
		Connection con=getConn();
		PreparedStatement st = con.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)");
		st.setLong(1, book.getBookId());
		st.setString(2, book.getTitle());
		st.setString(3, book.getAuthorName());
		
		st.setString(4, sdf2.format(book.getPublishedDtae()));
		st.setString(5, book.getCountry());
		st.setBytes(6, book.getPicture());
		int no=st.executeUpdate();
		return no;
	}
	@Override
	public List<Book> read() throws SQLException, ParseException, ClassNotFoundException
	{
		Connection con=getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM book");
		ResultSet rs = st.executeQuery();
		List<Book> bookList=new ArrayList<Book>();
		while(rs.next())
		{
			Book book=new Book(rs.getLong(1), rs.getString(2), rs.getString(3), sdf.parse(rs.getString(4)), rs.getString(5), rs.getBytes(6));
			bookList.add(book);
		}
		return bookList;
		
	}
	
	@Override
	public Book read(Long bookId) throws ClassNotFoundException, SQLException, ParseException
    {
        Connection con = getConn();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Book WHERE bookId=?");
        st.setLong(1, bookId);
        ResultSet rs = st.executeQuery();
        Book book=null;
        if(rs.next())
            book=new Book(rs.getLong(1), rs.getString(2), rs.getString(3), sdf.parse(rs.getString(4)), rs.getString(5), rs.getBytes(6));
        return book;
    }
	@Override
	public int update(Book book) throws ClassNotFoundException, SQLException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE Book SET title=?, authorname=?, publisheddate=?, country=?, picture=? WHERE bookId=?");
				
		st.setString(1, book.getTitle());
		st.setString(2, book.getAuthorName());
		st.setString(3, sdf.format(book.getPublishedDtae()));
		st.setString(4, book.getCountry());
		st.setBytes(5, book.getPicture());
		st.setLong(6, book.getBookId());

		int no=st.executeUpdate();
		return no;
	}
	
	@Override
	public int delete(Long bookId) throws ClassNotFoundException, SQLException
    {
        Connection con = getConn();
        PreparedStatement st = con.prepareStatement("DELETE FROM Book WHERE bookId=?");
                
        st.setLong(1, bookId);
        int no=st.executeUpdate();
        return no;
    }
}
