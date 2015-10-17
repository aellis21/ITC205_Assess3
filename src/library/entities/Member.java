package library.entities;

import java.util.List;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Member implements IMember {


	String _firstName, _lastName, _contactPhone, _emailAddress;
	int _ID;
	float finesPayable;
	EMemberState _state;
	List<ILoan> loans;
	
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
		
	}
	
	
	@Override
	public void addLoan(ILoan loan) {
		if (!hasReachedLoanLimit())
			loans.add(loan);
	}

	@Override
	public void removeLoan(ILoan loan) {
		loans.remove(loan);
	}
	
	@Override
	public List<ILoan> getLoans() {
		return loans;
	}
	
	@Override
	public boolean hasOverDueLoans() {
		
		int overDueCounter = 0;
		
		for (int i = 0; i < loans.size(); i++){
			if (loans.get(i).isOverDue()){
				overDueCounter++;
			}
		}
		
		if (overDueCounter > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasReachedLoanLimit() {
		if (loans.size() >= IMember.LOAN_LIMIT)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasFinesPayable() {
		if (finesPayable > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasReachedFineLimit() {
		if (loans.size() >= IMember.FINE_LIMIT)
			return true;
		else
			return false;
	}

	@Override
	public float getFineAmount() {
		return finesPayable;
	}

	@Override
	public void addFine(float fine) {
		if (loans.size() >= IMember.FINE_LIMIT)
			throw new RuntimeException("Fine limit reached");
		else
			finesPayable += fine;
	}

	@Override
	public void payFine(float payment) {
		if (loans.size() >= IMember.FINE_LIMIT)
			throw new RuntimeException("Fine limit reached");
		else
			finesPayable -= payment;

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
