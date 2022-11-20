package group5.libraryManagementWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.service.CartService;

@Controller
@RequestMapping(value = "/user/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping(value = "")
	public String books(ModelMap model) {
		Loan loan = new Loan();
		model.addAttribute("loan", loan);
		model.addAttribute("books", cartService.getBooksInCart());
		model.addAttribute("booksQuantity", cartService.getBooksQuantityInCart());
		return "user/cart";
	}

	@GetMapping(value = "/add/{id}")
	public String add(RedirectAttributes attributes, @PathVariable("id") Long id) {
		cartService.addBook(id);
		return "redirect:/user/cart";
	}

	@GetMapping(value = "/remove/{id}")
	public String remove(@PathVariable("id") Long id) {
		cartService.removeBook(id);
		return "redirect:/user/cart";
	}

	@PostMapping(value = "/createNewLoan")
	public String createNewLoan(ModelMap model, Loan loan) {
		cartService.createLoan(loan);
		return "redirect:/user/cart";
	}
}
