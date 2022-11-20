package group5.libraryManagementWebApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group5.libraryManagementWebApp.repository.BookOnLoanRepository;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.BookService;
import group5.libraryManagementWebApp.model.Book;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookOnLoanRepository bookOnLoanRepository;

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public void addBook(Book book) {
		// Kiểm tra xem đã tồn tại trong CSDL hay chưa
//		if (bookRepository.existByTitleAndAuthorNameAndPublishedYear(book.getTitle(), book.getAuthorName(), book.getPublishedYear())) {
//			bookRepository.save(book);
//		} else {
//			//
//		}
		bookRepository.save(book);
	}

	public void editBook(Long id, Book book) {
		Book oldBook = bookRepository.getReferenceById(id);
		oldBook.setTitle(book.getTitle());
		oldBook.setAuthorName(book.getAuthorName());
		oldBook.setCategory(book.getCategory());
		oldBook.setPublishedYear(book.getPublishedYear());
		oldBook.setAvailable(book.getAvailable());
		bookRepository.flush();
	}

	public void updateAvailable(Long id, Integer quantity) {
		bookRepository.getReferenceById(id).setAvailable(quantity);
	}

}