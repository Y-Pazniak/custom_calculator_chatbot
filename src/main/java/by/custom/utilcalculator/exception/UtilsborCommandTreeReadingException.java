package by.custom.utilcalculator.exception;


import java.net.URISyntaxException;

public class UtilsborCommandTreeReadingException extends URISyntaxException {
    public UtilsborCommandTreeReadingException(final String message, final Throwable cause){
        super(message, cause.getMessage());
    }
}