package group5.libraryManagementWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group5.libraryManagementWebApp.service.CartService;

@Controller
@RequestMapping(value = "/user/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	

	
    @GetMapping(value = "")
    public String books(ModelMap model) {
        model.addAttribute("books", cartService.getBooksInCart().entrySet());
        return "user/cart";
    }
    
    @GetMapping(value = "/add/{id}") 
    public String add(RedirectAttributes attributes, @PathVariable("id") Long id) {
    	cartService.addBook(id);
    	attributes.addAttribute("message", "Đầu sách có id: " + id + " đã được thêm vào giỏ hàng");
    	return "redirect:/user/cart";
    }
    
    @GetMapping(value = "/remove/{id}") 
    public String remove(@PathVariable("id") Long id) {
    	cartService.removeBook(id);
    	return "redirect:/user/cart";
    }
}
