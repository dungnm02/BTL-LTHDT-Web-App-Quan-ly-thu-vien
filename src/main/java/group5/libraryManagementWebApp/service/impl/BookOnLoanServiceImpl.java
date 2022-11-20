package group5.libraryManagementWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.BookOnLoanRepository;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.repository.LoanRepository;
import group5.libraryManagementWebApp.service.BookOnLoanService;

@Service
public class BookOnLoanServiceImpl implements BookOnLoanService {
	@Autowired
	BookOnLoanRepository bookOnLoanRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	LoanRepository loanRepository;

	public void createBookOnLoan(BookOnLoan bookOnLoan) {
		bookOnLoanRepository.save(bookOnLoan);
	}

	public void deleteBookOnLoan(Long id) {
		bookOnLoanRepository.deleteById(id);
	}

	public Book getBook(BookOnLoan bookOnLoan) {
		return bookOnLoan.getBook();
	}

	public Integer getBookQuantity(BookOnLoan bookOnLoan) {
		return bookOnLoan.getQuantity();
	}

	public Loan getLoan(BookOnLoan bookOnLoan) {
		return bookOnLoan.getLoan();
	}

}
