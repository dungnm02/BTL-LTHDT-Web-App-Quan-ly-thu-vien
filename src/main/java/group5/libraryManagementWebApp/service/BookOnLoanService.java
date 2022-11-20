package group5.libraryManagementWebApp.service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;

public interface BookOnLoanService {
	public void createBookOnLoan(BookOnLoan bookOnLoan);

	public void deleteBookOnLoan(Long id);

	public Book getBook(BookOnLoan bookOnLoan);

	public Integer getBookQuantity(BookOnLoan bookOnLoan);

	public Loan getLoan(BookOnLoan bookOnLoan);
}
