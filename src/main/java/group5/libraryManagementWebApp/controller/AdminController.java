package group5.libraryManagementWebApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@GetMapping(value = { "", "/index" })
	public String index() {
		return "admin/index";
	}

	@GetMapping(value = "/books")
	public String books(ModelMap model, Optional<String> message) {
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		model.addAttribute("books", bookService.getBooks());
		return "admin/books";
	}
    @GetMapping(value = "/books/search") 
    public String searchBooks (ModelMap model, @RequestParam(name="keyword", required = false) String keyword) {
    	List<Book> books = null;
    	if (!keyword.isEmpty()) {
    		books = bookRepository.findByTitleContainingOrAuthorNameContainingIgnoreCase(keyword, keyword);
    	} else {
    		books = bookRepository.findAll();
    	}
    	System.out.print(keyword);
    	System.out.print(books.size());
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

	@GetMapping(value = "/loans")
	public String loans(ModelMap model, Optional<String> message) {
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		model.addAttribute("loans", loanService.getLoans());

		return "admin/loans";
	}
	
    @GetMapping(value = "/loans/search") 
    public String searchLoans (ModelMap model, @RequestParam(name="keyword", required = false) Long keyword) {
    	List<Loan> loans = new ArrayList<>();
    	if (loanRepository.existsById(keyword)) {
    		loans.add(loanRepository.getReferenceById(keyword));
    	} else {
    		loans = loanRepository.findAll();
    	}
    	System.out.print(keyword);
    	System.out.print(loans.size());
    	return "admin/searchLoans";
    }
    
	@GetMapping(value = "/loans/{id}")
	public String loan(ModelMap model, @PathVariable("id") Long id) {
		Loan loan = loanRepository.getReferenceById(id);
		model.addAttribute("loan", loan);
		List<BookOnLoan> booksOnLoan = loan.getBooksOnLoan();
		model.addAttribute("booksOnLoan", booksOnLoan);
		List<Book> books = new ArrayList<>();
		for (BookOnLoan bookOnLoan : booksOnLoan) {
			books.add(bookOnLoan.getBook());
		}
		model.addAttribute("books", books);
		return "admin/loan";
	}

	@GetMapping(value = "/addBook")
	public String addBook(ModelMap model) {
		Book newBook = new Book();
		model.addAttribute("book", newBook);
		return "admin/addBook";
	}

	@PostMapping(value = "/saveNewBook")
	public String saveBook(ModelMap model, Book book) {
		bookService.addBook(book);
		return "admin/addBook";
	}

}
