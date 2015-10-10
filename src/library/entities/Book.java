package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

	String _author, _title, _callNumber;
	int _bookID;
	EBookState _state;
	public ILoan _loan;
	
	public Book (String author, String title, String callNumber, int bookID){
		
		if (author == null || author == "" ||
				title == null || title == "" ||
				callNumber == null || callNumber == "" ){
			throw new IllegalArgumentException("Parameters cannot be null or blank");
		}
		if (bookID <= 0){
			throw new IllegalArgumentException("BookID cannot be 0 or less");
		}
		
		_author = author;
		_title = title;
		_callNumber = callNumber;
		_bookID = bookID;
		
		
	}
	
	@Override
	public void setState(EBookState state){
		_state = state;
	}
	
	@Override
	public void borrow(ILoan loan) {
		if (_state != EBookState.AVAILABLE){
			throw new RuntimeException("Book not available");
		}
		_loan = loan;
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
		if(_state == EBookState.ON_LOAN)
			throw new RuntimeException("Book not available");
		if (damaged)
			_state = EBookState.DAMAGED;
		else
			_state = EBookState.AVAILABLE;

		_loan = null;
	}

	@Override
	public void lose() {
		if(_state == EBookState.ON_LOAN){
			throw new RuntimeException("Book not available");
		}
		_state = EBookState.LOST;

	}

	@Override
	public void repair() {
		if(_state != EBookState.DAMAGED)
			throw new RuntimeException("Book not damaged");
		_state = EBookState.AVAILABLE;
	}

	@Override
	public void dispose() {
		if(_state != EBookState.DAMAGED &&
				_state != EBookState.AVAILABLE &&
				_state != EBookState.LOST)
			throw new RuntimeException("Book not damaged, available or lost");
		_state = EBookState.DISPOSED;

	}

	@Override
	public EBookState getState() {
		return _state;
	}

	@Override
	public String getAuthor() {
		return _author;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getCallNumber() {
		return _callNumber;
	}

	@Override
	public int getID() {
		return _bookID;
	}

}
