package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

import java.io.IOException;

public class TreeReadingException extends IOException {
    public TreeReadingException(final String message, final Throwable cause){
        super(message, cause);
    }
}