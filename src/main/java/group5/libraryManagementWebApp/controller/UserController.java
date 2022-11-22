package group5.libraryManagementWebApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.repository.BookRepository;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private BookRepository bookRepository;

	// INDEX
	@GetMapping(value = { "", "/index" })
	public String index() {
		return "user/index";
	}

	// BOOKS
	@GetMapping(value = "/books")
	public String books(ModelMap model) {
		model.addAttribute("books", bookRepository.findAll());
		return "user/books";
	}

	@GetMapping(value = "/books/search")
	public String search(ModelMap model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Book> books = null;
		if (!keyword.isEmpty()) {
			// Tìm các quyển sách có tên hoặc tên tác giả chứa keyword
			books = bookRepository.findByTitleContainingOrAuthorNameContainingIgnoreCase(keyword, keyword);
		} else {
			// Lấy toàn bộ
			books = bookRepository.findAll();
		}
		model.addAttribute("books", books);
		return "user/searchBooks";
	}

	// CONTACT
	@GetMapping(value = "/contact")
	public String contact() {
		return "user/contact";
	}

}
