package group5.libraryManagementWebApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.repository.BookRepository;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "admin/index";
    }

    @GetMapping(value = "/books")
    public String books(ModelMap model, Optional<String> message) {
    	 Iterable<Book> books = bookRepository.findAll();
         
         if (message.isPresent()) {
             model.addAttribute("message", message.get());
         }
         
         model.addAttribute("books", books);
         return "admin/books";
    }
    
    @GetMapping(value = "/delete/{id}")
    public String delete(RedirectAttributes attributes, @PathVariable("id") Long id) {
    	bookRepository.deleteById(id);
    	
    	attributes.addAttribute("message", "Đầu sách có id: " + id + " đã được xóa");
    	return "redirect:/admin/books";
    }
    

    @GetMapping(value = "/orders")
    public String contact() {
        return "admin/orders";
    }

    @GetMapping(value = "/add_book")
    public String addBook() {
        return "admin/add_book";
    }

}
