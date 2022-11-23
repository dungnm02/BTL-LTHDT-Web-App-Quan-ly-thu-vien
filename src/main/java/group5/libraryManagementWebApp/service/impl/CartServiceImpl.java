package group5.libraryManagementWebApp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.repository.BookOnLoanRepository;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.CartService;
import group5.libraryManagementWebApp.service.EmailService;
import group5.libraryManagementWebApp.service.LoanService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LoanService loanService;
	@Autowired
	private BookOnLoanRepository bookOnLoanRepository;
	@Autowired
	private EmailService emailService;

	private HashMap<Long, Integer> booksQuantityInCartById = new HashMap<>();

	public void addBook(Long id) {
		Book book = bookRepository.getReferenceById(id);

		if (booksQuantityInCartById.containsKey(id)) {
			// Nếu có rồi và số lượng hiện tại vẫn đủ để thêm -> +1 số lượng
			if (booksQuantityInCartById.get(id) < book.getAvailable()) {
				booksQuantityInCartById.put(id, booksQuantityInCartById.get(id) + 1);
			}
		} else if (book.getAvailable() > 0) {
			// Nếu chưa có và số lượng hiện tại vẫn đủ để thêm -> số lượng = 1
			booksQuantityInCartById.put(id, 1);
		}

	}

	public void removeBook(Long id) {
		// Giảm 1 nếu > 0, nhỏ hơn thì xóa khỏi giỏ hàng
		if (booksQuantityInCartById.get(id) > 1) {
			booksQuantityInCartById.put(id, booksQuantityInCartById.get(id) - 1);
		} else {
			booksQuantityInCartById.remove(id);
		}
	}

	public List<Book> getBooksInCart() {
		// Lấy sách trong giỏ hàng (Không kèm theo số lượng của chúng)
		List<Book> books = new ArrayList<>();
		for (Long id : booksQuantityInCartById.keySet()) {
			books.add(bookRepository.getReferenceById(id));
		}
		return books;
	}

	public List<Integer> getBooksQuantityInCart() {
		// Lấy số lượng sách trong giỏ hàng (Không kèm biết sách là gì?)
		List<Integer> booksQuantity = new ArrayList<>();
		for (Integer quantity : booksQuantityInCartById.values()) {
			booksQuantity.add(quantity);
		}
		return booksQuantity;
	}

	public void createLoan(Loan loan) {
		// Tạo đơn mượn nếu số sách > 1
		if (booksQuantityInCartById.size() < 1) {
			return;
		}
		loanService.createLoan(loan);
		loan.setBooksOnLoan(new ArrayList<>());

		for (Map.Entry<Long, Integer> entry : booksQuantityInCartById.entrySet()) {
			// Tạo quan hệ trên CSDL
			BookOnLoan bookOnLoan = new BookOnLoan();
			Book book = bookRepository.getReferenceById(entry.getKey());
			bookOnLoan.setBook(book);
			bookOnLoan.setQuantity(entry.getValue());
			bookOnLoan.setLoan(loan);
			bookOnLoanRepository.save(bookOnLoan);
			loan.getBooksOnLoan().add(bookOnLoan);
			// Cập nhật số lượng sách
			book.setAvailable(book.getAvailable() - entry.getValue());

		}
		bookOnLoanRepository.flush();
		bookRepository.flush();
		emailService.sendMail(emailService.createNewEmail(loan));

		// Xóa sạch giỏ hàng
		booksQuantityInCartById.clear();
	}

}
