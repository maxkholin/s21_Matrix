public class MatrixExceptions extends RuntimeException {
    public MatrixExceptions(String message) {
        super(message);
    }
}

enum exceptionsMessages {
    INCORRECT_INITIALIZATION("Incorrect initialization: the number of rows or columns cannot be less than one"),
    DIFFERENT_DIMENSIONS("Different dimensions of matrices"),
    MISMATCHED_DIMENSIONS("The number of columns of the first matrix is not equal to the number of rows of the second matrix"),
    NOT_SQUARE("The matrix is not square"),
    ZERO_DETERMINANT("The determinant of the matrix is 0");

    private final String message;

    exceptionsMessages(String message) {
        this.message = message;
    }

    public static String getMessage(exceptionsMessages exception) {
        return exception.message;
    }
}
