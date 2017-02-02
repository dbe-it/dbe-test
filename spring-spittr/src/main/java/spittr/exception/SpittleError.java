package spittr.exception;

public class SpittleError {
    private int code;
    private String message;

    public SpittleError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
