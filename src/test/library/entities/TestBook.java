package test.library.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBook {

	String _author;
	String _title;
	String _callNumber;
	int _bookID;
	Book _book;
	ILoan _loan;

	@Before
	public void setUp() throws Exception {
		_author = "Author";
		_title = "Title";
		_callNumber = "CallNumber";
		_bookID = 1;

		_book = new Book(_author, _title, _callNumber, _bookID);
		_loan = mock(ILoan.class);
	}

	@After
	public void tearDown() throws Exception {
		_book = null;
		_loan = null;
	}

	@Test
	public void testCreate() {
		IBook book = new Book(_author, _title, _callNumber, _bookID);
		assertTrue(book instanceof Book);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullAuthor() {
		IBook book = new Book(null, _title, _callNumber, _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankAuthor() {
		IBook book = new Book("", _title, _callNumber, _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullTitle() {
		IBook book = new Book(_author, null, _callNumber, _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankTitle() {
		IBook book = new Book(_author, "", _callNumber, _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullNumber() {
		IBook book = new Book(_author, _title, null, _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankNumebr() {
		IBook book = new Book(_author, _title, "", _bookID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NegativeBookID() {
		IBook book = new Book(_author, _title, _callNumber, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_ZeroBookID() {
		IBook book = new Book(_author, _title, _callNumber, 0);
	}

	@Test
	public void testBorrow() {
		assertEquals(_book.getState(), EBookState.AVAILABLE);
		_book.borrow(_loan);
		assertEquals(_book.getState(), EBookState.ON_LOAN);
	}

}
