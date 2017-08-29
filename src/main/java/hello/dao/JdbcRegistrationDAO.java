package hello.dao;

import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//handles queries to database
@Repository
public class JdbcRegistrationDAO implements RegisterDAO {


	private final String USER_INSERT_QUERY = "insert into users (username, password, enabled) values(?,?,true);";
	private final String USER_ROLE_INSERT_QUERY = "insert into user_roles (username, role) values (?, 'ROLE_USER');";
	private final String SELECT_COUNT_USERS = "select count(*) from users where username = ?";
	private final String SELECT_ALL_USERS = "select * from users";

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

	@Override
	public Iterable<User> getAllUsers() {


		//RowMapper gets all rows from the Database and transforms them into objects
		//https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html
		List<User> users = this.jdbcTemplate.query(
				SELECT_ALL_USERS,
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUsername(rs.getString("username"));
						user.setPassword(rs.getString("password"));
						user.setConfirmPassword(rs.getString("password"));
						return user;
					}
				});

		return users;
	}
}
