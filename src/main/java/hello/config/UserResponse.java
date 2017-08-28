package hello.config;


//klasa - flaga
public class UserResponse {

	private boolean success;
	private String errorMessage;

	public UserResponse(){
		success = true;
	}

	public UserResponse(String errorMessage) {
		success = false;
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
