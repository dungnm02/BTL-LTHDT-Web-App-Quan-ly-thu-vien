package group5.libraryManagementWebApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/index" })
	public String redirectToHome() {
		return "user/index.html";
	}

	@GetMapping(value = "/books")
	public String redirectToBooks() {
		return "user/books.html";
	}

	@GetMapping(value = "/contact")
	public String redirectToContact() {
		return "user/contact.html";
	}

	@GetMapping(value = { "/admin/", "/admin/index" })
	public String redirectToAdminIndex() {
		return "admin/index.html";
	}

	@GetMapping(value = "/admin/books")
	public String redirectToAdminBooks() {
		return "admin/books.html";
	}

	@GetMapping(value = "/admin/orders")
	public String redirectToAdminOrders() {
		return "admin/orders.html";
	}

	@GetMapping(value = "/admin/add_books")
	public String redirectToAdminAddBooks() {
		return "admin/add_books.html";
	}
}
