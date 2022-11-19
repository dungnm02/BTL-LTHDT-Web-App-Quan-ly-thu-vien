package group5.libraryManagementWebApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @GetMapping(value = "/searchByTitle") 
    public String search (ModelMap model, @RequestParam(""))

    @GetMapping(value = "/contact")
    public String contact() {
        return "user/contact";
    }
    
}
