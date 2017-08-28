package hello.dao;

import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//handles queries to database
@Repository
public class JdbcRegistrationDAO implements RegisterDAO {


	private final String USER_INSERT_QUERY = "insert into users (username, password, enabled) values(?,?,true);";
	private final String USER_ROLE_INSERT_QUERY = "insert into user_roles (username, role) values (?, 'ROLE_USER');";
	private final String SELECT_COUNT_USERS = "select count(*) from users where username = ?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcRegistrationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public boolean create(User user) {
		String username = user.getUsername();
		if (exist(username))
			return false;


		jdbcTemplate.update(USER_INSERT_QUERY, user.getUsername(), user.getPassword());
		jdbcTemplate.update(USER_ROLE_INSERT_QUERY, username);
		return true;

	}

	private boolean exist(String username) {
		Integer integer = jdbcTemplate.queryForObject(SELECT_COUNT_USERS, Integer.class, username);
		return integer > 0;
	}
}
