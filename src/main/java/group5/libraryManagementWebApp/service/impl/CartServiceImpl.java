package group5.libraryManagementWebApp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.BookOnLoanService;
import group5.libraryManagementWebApp.service.CartService;
import group5.libraryManagementWebApp.service.LoanService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LoanService loanService;
	@Autowired
	private BookOnLoanService bookOnLoanService;

	private HashMap<Long, Integer> booksQuantityInCartById = new HashMap<>();

	public void addBook(Long id) {
		Book book = bookRepository.getReferenceById(id);
		if (booksQuantityInCartById.containsKey(id)) {
			if (booksQuantityInCartById.get(id) < book.getAvailable()) {
				booksQuantityInCartById.put(id, booksQuantityInCartById.get(id) + 1);
			} else {
				
			}
		} else {
			booksQuantityInCartById.put(id, 1);
		}
	}

	public void removeBook(Long id) {
		if (booksQuantityInCartById.get(id) > 1) {
			booksQuantityInCartById.put(id, booksQuantityInCartById.get(id) - 1);
		} else {
			booksQuantityInCartById.remove(id);
		}
	}

	public List<Book> getBooksInCart() {
		List<Book> books = new ArrayList<>();
		for (Long id : booksQuantityInCartById.keySet()) {
			books.add(bookRepository.getReferenceById(id));
		}
		return books;
	}
	
	public List<Integer> getBooksQuantityInCart() {
		List<Integer> booksQuantity = new ArrayList<>();
		for (Integer quantity : booksQuantityInCartById.values()) {
			booksQuantity.add(quantity);
		}
		return booksQuantity;
	}

	public void createLoan(Loan loan) {
		if (booksQuantityInCartById.size() < 1) {
			return;
		}
		loanService.createLoan(loan);
		for (Map.Entry<Long, Integer> entry : booksQuantityInCartById.entrySet()) {
			BookOnLoan bookOnLoan = new BookOnLoan();
			bookOnLoan.setBook(bookRepository.getReferenceById(entry.getKey()));
			bookOnLoan.setQuantity(entry.getValue());
			bookOnLoan.setLoan(loan);
			bookOnLoanService.createBookOnLoan(bookOnLoan);
		}

	}

}
