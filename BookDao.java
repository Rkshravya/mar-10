package model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface BookDao {

	int create(Book book) throws SQLException, ClassNotFoundException;

	List<Book> read() throws SQLException, ParseException, ClassNotFoundException;

	Book read(Long bookId) throws ClassNotFoundException, SQLException, ParseException;

	int update(Book book) throws ClassNotFoundException, SQLException;

	int delete(Long bookId) throws ClassNotFoundException, SQLException;

}