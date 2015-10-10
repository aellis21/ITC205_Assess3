package library.entities;

import java.util.Calendar;
import java.util.Date;

import library.interfaces.entities.ELoanState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Loan implements ILoan {

	IBook _book;
	IMember _member;
	Date _borrowDate, _dueDate;
	Calendar _calendar;
	ELoanState _state;
	int _loanID;

	
	public Loan (IBook book, IMember member, Date borrowDate, Date dueDate){
		if (book == null || 
				member == null ||
				borrowDate == null ||
				dueDate == null){
			throw new IllegalArgumentException("Parameters cannot be null");
		}
		if (dueDate.compareTo(borrowDate) < 0){
			throw new IllegalArgumentException("Due date before borrow date");
		}
		
		_book = book;
		_member = member;
		_borrowDate = borrowDate;
		_dueDate = dueDate;
		_state = ELoanState.PENDING;
	}
	
	public void setOverDue(){
		_state = ELoanState.OVERDUE;
	}
	
	@Override
	public void commit(int loanID) {
		if (loanID <= 0){
			throw new IllegalArgumentException("Loan ID must be positive integer");
		}
		if (_state != ELoanState.PENDING){
            throw new RuntimeException("Loan 'state' not pending." + _state);
		}
		
        
        _loanID = loanID;
        
        _book.borrow(this);
        _member.addLoan(this);
        
        _state = ELoanState.CURRENT;
	}
	
	@Override
	public void complete() {
		if (_state == ELoanState.CURRENT || _state == ELoanState.OVERDUE){
			throw new RuntimeException("Loan state not current");
		}
		
		_state = ELoanState.COMPLETE;
	}
	
	@Override
	public boolean isCurrent() {
		return _state == ELoanState.CURRENT;
	}

	@Override
	public boolean isOverDue() {
		return _state == ELoanState.OVERDUE;
	}

	@Override
	public boolean checkOverDue(Date currentDate) {
		if (_state == ELoanState.CURRENT || _state == ELoanState.OVERDUE){
			throw new RuntimeException("Loan state not current or overdue");
		}
		
		return currentDate.after(_dueDate);
	}

	@Override
	public IMember getBorrower() {
		return _member;
	}

	@Override
	public IBook getBook() {
		return _book;
	}

	@Override
	public int getID() {
		return _book.getID();
	}

}
