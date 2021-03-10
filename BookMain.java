package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Book;
import model.BookDaoImpl;

public class BookMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, Exception 
	{
		BookDaoImpl adao=new BookDaoImpl();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		Scanner sc=new Scanner(System.in);
		do
		{
			 Long bookId;
			 String title;
			 String authorName;
			 Date publishedDate;
			 String country;
			 byte[] picture;
			 Book book=null;
			 System.out.println("	1) Add Book\r\n" + 
						"	2) Update Book\r\n" + 
						"	3) Remove Book\r\n" + 
						"	4) Display All Books\r\n" + 
						"	5) Find Book by Id\r\n" + 
						"	6) Exit\r\n" + 
						"");
			 int choice=sc.nextInt();
				switch(choice)
				{
				case 1:			//add
					System.out.println("Please provide the values to add a new book");
					System.out.println("Book Id:");
					bookId=sc.nextLong();
					System.out.println("title Name:");
					title=sc.nextLine();
					if(title.equals(""))
						title=sc.nextLine();
					System.out.println("author Name:");
					authorName=sc.nextLine();
					System.out.println("Published date: (dd-MMM-yy)");
					publishedDate=sdf.parse(sc.nextLine());
					System.out.println("Country:");
					country=sc.nextLine();
					System.out.println("Picture: (pls enter the full path)");
					File f=new File(sc.nextLine());
					int len=(int)f.length();
					picture=new byte[len];
					FileInputStream fis=new FileInputStream(f);
					fis.read(picture);
					fis.close();
					book=new Book(bookId, title, authorName, publishedDate, country, picture);
					System.out.println(book);
					adao.create(book);
					System.out.println("Added...");
					
					break;
				case 2:
					break;
				case 3:			
					System.out.println("Please provide the book ID to remove the book");
					System.out.println("book Id:");
					bookId=sc.nextLong();
					adao.delete(bookId);
					System.out.println("Deleted...");
					break;
				case 4:		
//					System.out.format("%-7s %-10s %-10s %-10s %-10s %10s\n", args)
					List<Book> books = adao.read();
					for(Book a : books)
						System.out.println(a);
					break;
				case 5:			
					System.out.println("Please provide the book ID to find the book");
					System.out.println("book Id:");
					bookId=sc.nextLong();
					book=adao.read(bookId);
					System.out.println(book);
					break;
				default:		
						System.exit(0);
					break;	
				}
		}while(true);
	}

}


