package hello.service;

import hello.config.UserResponse;
import hello.dao.RegisterDAO;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by andrzej on 28.08.17.
 */
@Service
public class UserService {

	private RegisterDAO registerDAO;

	@Autowired
	public UserService (RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	public UserResponse create(User user) {
		if(!Objects.equals(user.getPassword(), user.getConfirmPassword())) {
			return new UserResponse("Passwords dont match");
		}

		boolean b = registerDAO.create(user);
		return b ? new UserResponse() : new UserResponse(user.getUsername() + " already exist");

	}
}
