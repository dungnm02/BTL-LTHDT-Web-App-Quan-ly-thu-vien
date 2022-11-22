package group5.libraryManagementWebApp.service;

import java.util.List;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.Loan;

public interface LoanService {
	public List<Loan> getNewLoans();

	public List<Loan> getAcceptedLoans();

	public List<Loan> getInProgressLoans();

	public List<Loan> getCompletedLoans();

	public List<Loan> getExpiredLoans();

	public List<Loan> getCancelledLoans();

	public void createLoan(Loan loan);

	public void completeLoan(Loan loan);

	public void checkExpiredLoans();

	public List<Book> getBooks(Long id);
}
