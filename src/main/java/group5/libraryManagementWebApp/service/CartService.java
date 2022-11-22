package group5.libraryManagementWebApp.service;

import java.util.List;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.Loan;

public interface CartService {

	public void addBook(Long id);

	public void removeBook(Long id);

	public List<Book> getBooksInCart();

	public List<Integer> getBooksQuantityInCart();

	public void createLoan(Loan loan);
}
