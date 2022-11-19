package group5.libraryManagementWebApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.service.BookService;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/index"})
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
    
    @GetMapping(value = "/delete/{id}")
    public String delete(RedirectAttributes attributes, @PathVariable("id") Long id) {
    	bookService.deleteBook(id);
    	attributes.addAttribute("message", "Đầu sách có id: " + id + " đã được xóa");
    	return "redirect:/admin/books";
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
    

    @GetMapping(value = "/orders")
    public String contact() {
        return "admin/orders";
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
