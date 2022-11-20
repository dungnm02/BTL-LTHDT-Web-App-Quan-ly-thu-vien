package group5.libraryManagementWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author dungn
 */
@Controller
public class HomeController {

	@GetMapping
	public String redirectToUser() {
		return "redirect:/user/index";
	}
}
