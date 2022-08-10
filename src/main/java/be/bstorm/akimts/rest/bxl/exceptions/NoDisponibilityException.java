package be.bstorm.akimts.rest.bxl.exceptions;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;

public class NoDisponibilityException extends RuntimeException {
    public NoDisponibilityException(LocalDate date) {
        super("there is no more availability for this day ("+date+")");
    }
}
