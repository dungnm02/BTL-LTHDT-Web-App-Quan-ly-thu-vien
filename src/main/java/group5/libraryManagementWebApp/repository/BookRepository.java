package group5.libraryManagementWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group5.libraryManagementWebApp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	//Tìm kiếm bằng title hoặc authorName - ignorecase
	List<Book> findByTitleContainingOrAuthorNameContainingIgnoreCase(String title, String AuthorName);
}
