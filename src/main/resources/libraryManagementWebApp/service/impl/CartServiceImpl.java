package group5.libraryManagementWebApp.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.CartService;

@Service
public class CartServiceImpl implements CartService{	
	@Autowired
	private BookRepository bookRepository;
	private HashMap<Book, Integer> booksInCart = new HashMap<>();
	
	public void addBook(Long id) {
		Book book = bookRepository.getReferenceById(id);
		if (booksInCart.containsKey(book)) {
			if (booksInCart.get(book) < book.getAvailable()) {
				booksInCart.put(book, booksInCart.get(book) + 1);
			} else {
				
			}
		} else {
			booksInCart.put(book, 1);
		}
	}
	
	public void removeBook(Long id) {
		Book book = bookRepository.getReferenceById(id);
		if (booksInCart.get(book) > 1) {
			booksInCart.put(book, booksInCart.get(book) - 1);
		} else {
			booksInCart.remove(book);
		}
	}
	
	public HashMap<Book, Integer> getBooksInCart() {
		return booksInCart;
	}
	
	public void createLoan() {
		
	}
	
}
