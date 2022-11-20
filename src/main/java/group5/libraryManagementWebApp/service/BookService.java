package group5.libraryManagementWebApp.service;

import java.util.List;

import group5.libraryManagementWebApp.model.Book;

public interface BookService {
	public List<Book> getBooks();

	public void deleteBook(Long id);

	public void addBook(Book book);

	public void editBook(Long id, Book book);

	public void updateAvailable(Long id, Integer quantity);
}
