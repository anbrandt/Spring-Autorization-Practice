package hello.config;

import hello.service.UserService;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by andrzej on 28.08.17.
 */

@Controller
public class UserController {

  	private UserService userService;

  	@Autowired
	public UserController (UserService userService) {
  		this.userService = userService;
	}


  	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
  		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerOk(@ModelAttribute User user, Model model) {

		UserResponse userResponse = userService.create(user);
		model.addAttribute("success", userResponse.isSuccess());
		model.addAttribute("message", userResponse.getErrorMessage());
		return "registerok";
	}


	@RequestMapping (value = "/adminmanager", method = RequestMethod.GET)
	public String getAllUsers(Model model) {

		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("users" , allUsers);

		return "adminmanager";
	}


}
