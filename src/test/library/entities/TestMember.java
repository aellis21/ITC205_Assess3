package test.library.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import library.entities.Member;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.interfaces.entities.ILoan;

public class TestMember {

	String _firstName;
	String _lastName;
	String _contactPhone;
	String _emailAddress;
	int _memberID;
	ILoan _loan;
	Member _member;

	@Before
	public void setUp() throws Exception {
		_firstName = "FName";
		_lastName = "LName";
		_contactPhone = "Contact#";
		_emailAddress = "EmailAddress";
		_memberID = 1;
		
		_member = new Member(_firstName, _lastName, _contactPhone,_emailAddress, _memberID);
		
		_loan = mock(ILoan.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		Member member = new Member(_firstName, _lastName, _contactPhone,
				_emailAddress, _memberID);
		assertTrue(member instanceof Member);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullFirstName() {
		Member member = new Member(null, _lastName, _contactPhone,
				_emailAddress, _memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankFirstName() {
		Member member = new Member("", _lastName, _contactPhone, _emailAddress,
				_memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullLastName() {
		Member member = new Member(_firstName, null, _contactPhone,
				_emailAddress, _memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankLastName() {
		Member member = new Member(_firstName, "", _contactPhone,
				_emailAddress, _memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullContactPhone() {
		Member member = new Member(_firstName, _lastName, null, _emailAddress,
				_memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankContactPhone() {
		Member member = new Member(_firstName, _lastName, "", _emailAddress,
				_memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullEmailAddress() {
		Member member = new Member(_firstName, _lastName, _contactPhone, null,
				_memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_BlankEmailAddress() {
		Member member = new Member(_firstName, _lastName, _contactPhone, "",
				_memberID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NegativeID() {
		Member member = new Member(_firstName, _lastName, _contactPhone,
				_emailAddress, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreate_ZeroID() {
		Member member = new Member(_firstName, _lastName, _contactPhone,
				_emailAddress, 0);
	}
	
	@Test
	public void testAddAndRemoveLoan(){
		_member.addLoan(_loan);
		assertTrue(_member.getLoans().contains(_loan));
		_member.removeLoan(_loan);
		assertFalse(_member.getLoans().contains(_loan));	
	}
	
	@Test
	public void testFines(){
		_member.addFine(5);
		assertTrue(_member.getFineAmount() > 0);
	}

}
