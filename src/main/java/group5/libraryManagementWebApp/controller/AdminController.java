package group5.libraryManagementWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "admin/index";
    }

    @GetMapping(value = "/books")
    public String books() {
        return "admin/books";
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
