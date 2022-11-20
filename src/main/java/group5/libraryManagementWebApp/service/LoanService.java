package group5.libraryManagementWebApp.service;

import java.util.List;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.Loan;

public interface LoanService {
	public List<Loan> getLoans();

	public void createLoan(Loan loan);

	public void deleteLoan(Long id);
	
	public List<Book> getBooks(Long id);
}
