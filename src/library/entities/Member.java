package library.entities;

import java.util.ArrayList;
import java.util.List;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Member implements IMember {


	String _firstName, _lastName, _contactPhone, _emailAddress;
	int _ID;
	float _finesPayable;
	EMemberState _state;
	List<ILoan> _loans;
	
	public Member (String firstName, String lastName, String contactPhone, String emailAddress, int ID){
		
		if (firstName == "" || firstName == null ||
				lastName == "" || lastName == null ||
				contactPhone == "" || contactPhone == null ||
				emailAddress == "" || emailAddress == null){
			throw new IllegalArgumentException("Parameters cannot be null or blank");			
		}
		if (ID <= 0){
			throw new IllegalArgumentException("ID cannot be 0 or less");
		}
		
		_firstName = firstName;
		_lastName = lastName;
		_contactPhone = contactPhone;
		_emailAddress = emailAddress;
		_ID = ID;
		_loans = new ArrayList<>();
		
	}
	
	
	@Override
	public void addLoan(ILoan loan) {
		if (!hasReachedLoanLimit())
			_loans.add(loan);
		else
			throw new RuntimeException ("Loan limit reached");
	}

	@Override
	public void removeLoan(ILoan loan) {
		_loans.remove(loan);
	}
	
	@Override
	public List<ILoan> getLoans() {
		return _loans;
	}
	
	@Override
	public boolean hasOverDueLoans() {
		for (int i = 0; i < _loans.size(); i++){
			if (_loans.get(i).isOverDue()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasReachedLoanLimit() {
		if (_loans.size() >= IMember.LOAN_LIMIT)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasFinesPayable() {
		if (_finesPayable > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasReachedFineLimit() {
		if (_loans.size() >= IMember.FINE_LIMIT)
			return true;
		else
			return false;
	}

	@Override
	public float getFineAmount() {
		return _finesPayable;
	}

	@Override
	public void addFine(float fine) {
		if (getFineAmount() >= IMember.FINE_LIMIT)
			throw new RuntimeException("Fine limit reached");
		else
			_finesPayable += fine;
	}

	@Override
	public void payFine(float payment) {
		if (getFineAmount() < 0)
			throw new RuntimeException("No fine owed");
		else
			_finesPayable -= payment;

	}

	@Override
	public EMemberState getState() {
		return _state;
	}

	@Override
	public String getFirstName() {
		return _firstName;
	}

	@Override
	public String getLastName() {
		return _lastName;
	}

	@Override
	public String getContactPhone() {
		return _contactPhone;
	}

	@Override
	public String getEmailAddress() {
		return _emailAddress;
	}

	@Override
	public int getID() {
		return _ID;
	}

}
