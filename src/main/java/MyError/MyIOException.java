package MyError;

public class MyIOException extends IllegalArgumentException {
    public MyIOException(String message, int select) {
        super(select + message);
    }
}





