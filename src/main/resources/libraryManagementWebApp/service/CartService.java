package group5.libraryManagementWebApp.service;

import java.util.HashMap;

import group5.libraryManagementWebApp.model.Book;

public interface CartService {
	void addBook(Long id);
	void removeBook(Long id);
	HashMap<Book, Integer> getBooksInCart();
	void createLoan();
}
