package hello.dao;

import hello.entities.User;

/**
 * Created by andrzej on 28.08.17.
 */
public interface RegisterDAO {

	boolean create(User user);

	Iterable<User> getAllUsers();
}
