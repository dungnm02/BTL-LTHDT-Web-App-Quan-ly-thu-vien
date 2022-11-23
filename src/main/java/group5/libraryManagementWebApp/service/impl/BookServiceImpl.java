package group5.libraryManagementWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.BookService;
import group5.libraryManagementWebApp.model.Book;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	public void editBook(Long id, Book book) {
		Book oldBook = bookRepository.getReferenceById(id);
		if (!book.getTitle().strip().isEmpty()) {
			oldBook.setTitle(book.getTitle());
		}
		if (!book.getAuthorName().strip().isEmpty()) {
			oldBook.setAuthorName(book.getAuthorName());
		}
		if (!book.getCategory().strip().isEmpty()) {
			oldBook.setCategory(book.getCategory());
		}
		if (book.getPublishedYear() != null) {
			oldBook.setPublishedYear(book.getPublishedYear());
		}
		if (book.getAvailable() != null) {
			oldBook.setAvailable(book.getAvailable());
		}
		bookRepository.flush();
	}

}
