package schoolmanagementsystem.response;

public class CreateReadingResponse {

    private String message;

    public CreateReadingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
