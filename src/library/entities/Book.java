package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

	String _author, _title, _callNumber;
	int _bookID;
	EBookState _state;
	ILoan _loan;
	
	public Book (String author, String title, String callNumber, int bookID){
		
		if (author == null || author == "" ||
				title == null || title == "" ||
				callNumber == null || callNumber == "" ){
			throw new IllegalArgumentException("Parameters cannot be null or blank");
		}
		if (bookID <= 0){
			throw new IllegalArgumentException("Book ID is 0 or less");
		}
		
		_author = author;
		_title = title;
		_callNumber = callNumber;
		_bookID = bookID;
		
		
	}
	
	
	@Override
	public void borrow(ILoan loan) {
		if (_state != EBookState.AVAILABLE){
			throw new RuntimeException("Book not available");
		}
		loan.setBook(this);
	}

	@Override
	public ILoan getLoan() {
		if(_state == EBookState.ON_LOAN)
			return null;
		else
			return _loan;
	}

	@Override
	public void returnBook(boolean damaged) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void repair() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public EBookState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCallNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
