package group5.libraryManagementWebApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.LoanRepository;
import group5.libraryManagementWebApp.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepository;
	
	public List<Loan> getLoans() {
		return loanRepository.findAll();
	}

	public void createLoan(Loan loan) {
		loan.setStatus("NEW");
		loanRepository.save(loan);
	}

	public void deleteLoan(Long id) {
		loanRepository.deleteById(id);
	}
	
	public List<Book> getBooks(Long id) {
		List<Book> books = new ArrayList<>();
		for (BookOnLoan bookOnLoan : loanRepository.getReferenceById(id).getBooksOnLoan()) {
			books.add(bookOnLoan.getBook());
		}
		return books;
	}

}
