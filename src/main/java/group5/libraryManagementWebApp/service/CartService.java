package group5.libraryManagementWebApp.service;

import java.util.List;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.Loan;

public interface CartService {
	void addBook(Long id);
	void removeBook(Long id);
	public List<Book> getBooksInCart();
	public List<Integer> getBooksQuantityInCart();
	void createLoan(Loan loan);
}
