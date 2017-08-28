package hello.entities;

//not used entity
public class Role {

	private int user_role_id;
	private String username;
	private String role;

	public Role() {}

	public Role(int user_role_id, String username, String role) {
		this.user_role_id = user_role_id;
		this.username = username;
		this.role = role;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
