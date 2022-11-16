package group5.libraryManagementWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group5.libraryManagementWebApp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
