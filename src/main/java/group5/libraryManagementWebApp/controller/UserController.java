package group5.libraryManagementWebApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.repository.BookRepository;
import group5.libraryManagementWebApp.service.BookService;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;


    @GetMapping(value = {"", "/index"})
    public String index() {
        return "user/index";
    }
    

    @GetMapping(value = "/books")
    public String books(ModelMap model, Optional<String> message) {
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
        }
        model.addAttribute("books", bookService.getBooks());
        return "user/books";
    }
    
    @GetMapping(value = "/books/search") 
    public String search (ModelMap model, @RequestParam(name="keyword", required = false) String keyword) {
    	List<Book> books = null;
    	if (!keyword.isEmpty()) {
    		books = bookRepository.findByTitleContainingOrAuthorNameContainingIgnoreCase(keyword, keyword);
    	} else {
    		books = bookRepository.findAll();
    	}
    	System.out.print(keyword);
    	System.out.print(books.size());
    	model.addAttribute("books", books);
    	return "user/searchBooks";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "user/contact";
    }
    
}
