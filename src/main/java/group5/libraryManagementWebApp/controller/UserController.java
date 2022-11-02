package group5.libraryManagementWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dungn
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "user/index";
    }

    @GetMapping(value = "/books")
    public String books() {
        return "user/books";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "user/contact";
    }
}
