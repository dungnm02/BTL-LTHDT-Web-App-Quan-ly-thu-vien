package group5.libraryManagementWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group5.libraryManagementWebApp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	//boolean existByTitleAndAuthorNameAndPublishedYear(String title, String authorName, Integer publishedYear);
	List<Book> findByTitleContaining(String title);
	List<Book> findByAuthorNameContaining(String authorName);

}

