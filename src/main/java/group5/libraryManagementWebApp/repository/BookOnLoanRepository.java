package group5.libraryManagementWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group5.libraryManagementWebApp.model.BookOnLoan;

@Repository
public interface BookOnLoanRepository extends JpaRepository<BookOnLoan, Long> {
}
