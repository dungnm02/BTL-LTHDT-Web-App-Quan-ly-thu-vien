package group5.libraryManagementWebApp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.repository.LoanRepository;
import group5.libraryManagementWebApp.service.BookService;
import group5.libraryManagementWebApp.service.EmailService;
import group5.libraryManagementWebApp.service.LoanService;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private BookService bookService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LoanService loanService;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private EmailService emailService;

	// INDEX
	@GetMapping(value = { "", "/index" })
	public String index() {
		return "admin/index";
	}

	// BOOKS
	@GetMapping(value = "/books")
	public String books(ModelMap model) {
		model.addAttribute("books", bookRepository.findAll());
		return "admin/books";
	}

	@GetMapping(value = "/books/search")
	public String searchBooks(ModelMap model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Book> books = null;
		if (!keyword.isEmpty()) {
			// Tìm các quyển sách có tên hoặc tên tác giả chứa keyword
			books = bookRepository.findByTitleContainingOrAuthorNameContainingIgnoreCase(keyword, keyword);
		} else {
			// Lấy toàn bộ
			books = bookRepository.findAll();
		}
		model.addAttribute("books", books);
		return "admin/searchBooks";
	}

	@GetMapping(value = "/edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Long id) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "admin/editBook";
	}

	@PostMapping(value = "/saveEdit/{id}")
	public String saveEdit(ModelMap model, Book book, @PathVariable("id") Long id) {
		bookService.editBook(id, book);
		return "redirect:/admin/books";
	}

	// LOANS
	@GetMapping(value = "/loans")
	public String loans(ModelMap model) {
		// Chuyển status các đơn đã quá hạn thành EXPIRED
		loanService.checkExpiredLoans();
		loanRepository.flush();

		model.addAttribute("newLoans", loanService.getNewLoans());
		model.addAttribute("confirmedLoans", loanService.getAcceptedLoans());
		model.addAttribute("inProgressLoans", loanService.getInProgressLoans());
		model.addAttribute("completedLoans", loanService.getCompletedLoans());
		model.addAttribute("expiredLoans", loanService.getExpiredLoans());
		model.addAttribute("cancelledLoans", loanService.getCancelledLoans());

		return "admin/loans";
	}

	@GetMapping(value = "/loans/search")
	public String searchLoans(ModelMap model, @RequestParam(name = "keyword", required = false) Long keyword) {
		List<Loan> loans = new ArrayList<>();
		if (loanRepository.existsById(keyword)) {
			System.out.print(keyword);
			// Tìm các quyển sách có id bằng keyword
			loans.add(loanRepository.getReferenceById(keyword));
		} else {
			// Lấy toàn bộ
			loans = loanRepository.findAll();
		}
		model.addAttribute("loans", loans);
		return "admin/searchLoans";
	}

	@GetMapping(value = "/loans/{id}")
	public String loan(ModelMap model, @PathVariable("id") Long id) {
		// Lấy thông tin đơn mượn
		Loan loan = loanRepository.getReferenceById(id);
		model.addAttribute("loan", loan);

		Integer dateTillExpire = 0;
		model.addAttribute("dateTillExpire", dateTillExpire);

		// Lấy số lượng từng quyển sách
		List<BookOnLoan> booksOnLoan = loan.getBooksOnLoan();
		model.addAttribute("booksOnLoan", booksOnLoan);

		// Lấy thông tin sách
		List<Book> books = new ArrayList<>();
		for (BookOnLoan bookOnLoan : booksOnLoan) {
			books.add(bookOnLoan.getBook());
		}
		model.addAttribute("books", books);

		// Lấy các tùy chọn cập nhật trạng thái cho đơn hàng theo trạng thái hiện tại
		if (loan.getStatus().equals("NEW")) {
			model.addAttribute("nextStatus", new ArrayList<>(Arrays.asList("ACCEPT", "CANCEL")));

		} else if (loan.getStatus().equals("ACCEPTED")) {
			model.addAttribute("nextStatus", new ArrayList<>(Arrays.asList("INPROGRESS", "CANCEL")));

		} else if (loan.getStatus().equals("INPROGRESS")) {
			model.addAttribute("nextStatus", new ArrayList<>(Arrays.asList("COMPLETE", "CANCEL")));

		} else {
			model.addAttribute("nextStatus", new ArrayList<>());
		}
		return "admin/loan";
	}

	@PostMapping(value = "/updateLoan/{id}")
	public String updateLoan(ModelMap model, @PathVariable("id") Long id, Loan loan) {
		// Cập nhật trạng thái đơn mượn hiện tại và gửi mail.

		Loan currLoan = loanRepository.getReferenceById(id);
		if (loan.getStatus().equals("ACCEPT")) {
			currLoan.setStatus("ACCEPTED");
			emailService.sendMail(emailService.createAcceptEmail(currLoan));

		} else if (loan.getStatus().equals("INPROGRESS")) {
			// Đơn mượn đang thực hiện -> Ngày mượn sách = Ngày hôm nay
			// Đơn mượn đang thực hiện -> Ngày hết hạn = Ngày hôm nay + 14 ngày
			currLoan.setStatus("INPROGRESS");

			LocalDate now = LocalDate.now();
			currLoan.setBorrowDate(now);
			currLoan.setExpireDate(now.plusDays(14));

			emailService.sendMail(emailService.createInProgressEmail(currLoan));

		} else if (loan.getStatus().equals("COMPLETE")) {
			// Đơn mượn hoàn thành -> Ngày trả sách = Ngày hôm nay
			currLoan.setStatus("COMPLETED");

			LocalDate now = LocalDate.now();
			currLoan.setReturnDate(now);
			loanService.completeLoan(currLoan);

			emailService.sendMail(emailService.createCompleteEmail(currLoan));

		} else {
			currLoan.setStatus("CANCELLED");
			emailService.sendMail(emailService.createCancelEmail(currLoan));
		}

		loanRepository.flush();
		return "redirect:/admin/loans";
	}

	@PostMapping(value = "/updateExpireDate/{id}")
	public String updateExpireDate(ModelMap model, @PathVariable("id") Long id, @RequestParam Integer days) {
		// Gia hạn đơn mượn
		Loan loan = loanRepository.getReferenceById(id);
		loan.setExpireDate(loan.getBorrowDate().plusDays(days));
		loanRepository.flush();
		emailService.sendMail(emailService.createUpdateExpireDateEmail(loan));
		return "redirect:/admin/loans/" + id;
	}

	// ADDBOOKS
	@GetMapping(value = "/addBook")
	public String addBook(ModelMap model) {
		Book newBook = new Book();
		model.addAttribute("book", newBook);
		return "admin/addBook";
	}

	@PostMapping(value = "/saveNewBook")
	public String saveBook(ModelMap model, Book book) {
		bookRepository.save(book);
		bookRepository.flush();
		return "redirect:/admin/books";
	}

}
