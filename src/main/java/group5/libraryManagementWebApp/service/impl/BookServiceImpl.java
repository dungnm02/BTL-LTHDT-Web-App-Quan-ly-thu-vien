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
		oldBook.setTitle(book.getTitle());
		oldBook.setAuthorName(book.getAuthorName());
		oldBook.setCategory(book.getCategory());
		oldBook.setPublishedYear(book.getPublishedYear());
		oldBook.setAvailable(book.getAvailable());
		bookRepository.flush();
	}

}
