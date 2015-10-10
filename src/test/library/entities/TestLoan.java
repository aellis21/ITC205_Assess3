package test.library.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.interfaces.entities.*;
import library.entities.*;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;


public class TestLoan {
	
	IBook _book;
	IMember _member;
	ILoan _loan;
	Date _borrowDate, _dueDate;
	Calendar _calendar;

	@Before
	public void setUp() throws Exception {
		_book = mock(IBook.class);
		_member =  mock(IMember.class);
		
		_calendar = Calendar.getInstance();
		_borrowDate = _calendar.getTime();
		_calendar.add(Calendar.DATE, ILoan.LOAN_PERIOD);
		_dueDate = _calendar.getTime();
		
		_loan = new Loan(_book, _member, _borrowDate, _dueDate);

	}

	@After
	public void tearDown() throws Exception {
		_loan = null;
	}
	
	@Test
	public void testCreate(){
		ILoan loan = new Loan(_book, _member, _borrowDate, _dueDate);
		assertTrue (loan instanceof Loan);
	}
		
	@Test (expected=IllegalArgumentException.class)
	public void testCreateBadParamBookNull(){
		ILoan loan = new Loan(null, _member, _borrowDate, _dueDate);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateBadParamMemberNull(){
		ILoan loan = new Loan(_book, null, _borrowDate, _dueDate);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateBadParamBorrowDateNull(){
		ILoan loan = new Loan(_book, _member, null, _dueDate);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateBadParamDueDateNull(){
		ILoan loan = new Loan(_book, _member, _borrowDate, null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateBadParamDueDateLessThanBorrowDate(){
		ILoan loan = new Loan(_book, _member, _dueDate, _borrowDate);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCommitLoanIDNegative(){
		_loan.commit(-1);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCommitLoanIDZero_NoException(){
		_loan.commit(0);
	}

	@Test
	public void isCurrent_StatePending_NoException () {
		assertFalse(_loan.isCurrent());
	}
	
	@Test
	public void testComplete() {
		_loan.complete();
	}

	@Test
	public void testIsOverDue() {
		assertFalse(_loan.isOverDue());
	}

	@Test
	public void testCheckOverDue_OnDueDate() {
		assertFalse(_loan.checkOverDue(_dueDate));
	}


}
