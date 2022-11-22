package group5.libraryManagementWebApp.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.repository.LoanRepository;
import group5.libraryManagementWebApp.service.EmailService;
import group5.libraryManagementWebApp.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private EmailService emailService;

	public void createLoan(Loan loan) {
		// Tạo đơn mượn mới với trạng thái NEW
		loan.setStatus("NEW");
		loanRepository.save(loan);
		loanRepository.flush();
	}

	public void completeLoan(Loan loan) {
		// Cập nhật lại số sách trong thư viện sau khi người mượn trả sách
		for (BookOnLoan bookOnLoan : loan.getBooksOnLoan()) {
			Book book = bookOnLoan.getBook();
			book.setAvailable(book.getAvailable() + bookOnLoan.getQuantity());
		}
		bookRepository.flush();
	}

	public List<Book> getBooks(Long id) {
		// Lấy các đầu sách xuất hiện trong đơn mượn
		List<Book> books = new ArrayList<>();
		for (BookOnLoan bookOnLoan : loanRepository.getReferenceById(id).getBooksOnLoan()) {
			books.add(bookOnLoan.getBook());
		}
		return books;
	}

	@Override
	public void checkExpiredLoans() {
		// Cập nhật trạng thái tất cả các đơn mượn hôm nay hết hạn thành EXPIRED
		for (Loan loan : loanRepository.findByStatus("INPROGRESS")) {
			if (loan.getExpireDate() != null && loan.getExpireDate().compareTo(LocalDate.now()) <= 0) {
				emailService.sendMail(emailService.createExpireEmail(loan));
				loan.setStatus("EXPIRED");
			}
		}
	}

	@Override
	public List<Loan> getNewLoans() {
		return loanRepository.findByStatus("NEW");
	}

	@Override
	public List<Loan> getInProgressLoans() {
		return loanRepository.findByStatus("INPROGRESS");
	}

	@Override
	public List<Loan> getCompletedLoans() {
		return loanRepository.findByStatus("COMPLETED");
	}

	@Override
	public List<Loan> getExpiredLoans() {
		return loanRepository.findByStatus("EXPIRED");
	}

	@Override
	public List<Loan> getCancelledLoans() {
		return loanRepository.findByStatus("CANCELLED");
	}

	@Override
	public List<Loan> getAcceptedLoans() {
		return loanRepository.findByStatus("ACCEPTED");
	}

}
