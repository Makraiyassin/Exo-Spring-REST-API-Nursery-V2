package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.exceptions.*;
import be.bstorm.akimts.rest.bxl.model.dto.ErrorDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(ElementNotFoundException ex, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 404 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                );
    }

    @ExceptionHandler(ReferencedSuppresionException.class)
    public ResponseEntity<ErrorDTO> handleException( ReferencedSuppresionException ex, HttpServletRequest req ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 400 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                );
    }


    @ExceptionHandler(InvalidReferenceException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidReferenceException ex, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 422 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                                .addInfo("invalidReferences", ex.getNotFound())
                );
    }

    @ExceptionHandler(FormValidationException.class)
    public ResponseEntity<ErrorDTO> handleException(FormValidationException ex, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 400 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                                .addInfo("errors", ex.getMessages())
                );
    }

    @ExceptionHandler(NoDisponibilityException.class)
    public ResponseEntity<ErrorDTO> handleException(NoDisponibilityException ex, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 400 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                );
    }



//region geniricité exception
//    public static <E extends RuntimeException> ResponseEntity<ErrorDTO> allException( E ex, HttpServletRequest req, HttpStatus status){
//        return ResponseEntity.status(status)
//                .body(
//                        ErrorDTO.builder()
//                                .message(ex.getMessage())
//                                .receivedAt(LocalDateTime.now())
//                                .status(status.value())
//                                .method(HttpMethod.resolve(req.getMethod()))
//                                .path(req.getRequestURL().toString())
//                                .build()
//                );
//    }
//endregion
}




//region save exceptions
//    @ExceptionHandler(ElementNotFoundException.class)
//    public ResponseEntity<ErrorDTO> handleException(ElementNotFoundException ex, HttpServletRequest req){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(
//                        ErrorDTO.builder()
//                                .message(ex.getMessage())
//                                .receivedAt( LocalDateTime.now() )
//                                .status( 404 )
//                                .method( HttpMethod.resolve(req.getMethod()) )
//                                .path( req.getRequestURL().toString() )
//                                .build()
//                );
//    }
//
//    @ExceptionHandler(ReferencedSuppresionException.class)
//    public ResponseEntity<ErrorDTO> handleException( ReferencedSuppresionException ex, HttpServletRequest req ){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(
//                        ErrorDTO.builder()
//                                .message(ex.getMessage())
//                                .receivedAt( LocalDateTime.now() )
//                                .status( 400 )
//                                .method( HttpMethod.resolve(req.getMethod()) )
//                                .path( req.getRequestURL().toString() )
//                                .build()
//                );
//    }
//
//
//    @ExceptionHandler(InvalidReferenceException.class)
//    public ResponseEntity<ErrorDTO> handleException(InvalidReferenceException ex, HttpServletRequest req){
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
//                .body(
//                        ErrorDTO.builder()
//                                .message(ex.getMessage())
//                                .receivedAt( LocalDateTime.now() )
//                                .status( 422 )
//                                .method( HttpMethod.resolve(req.getMethod()) )
//                                .path( req.getRequestURL().toString() )
//                                .build()
//                                .addInfo("invalidReferences", ex.getNotFound())
//                );
//    }
//
//    @ExceptionHandler(FormValidationException.class)
//    public ResponseEntity<ErrorDTO> handleException(FormValidationException ex, HttpServletRequest req){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(
//                        ErrorDTO.builder()
//                                .message(ex.getMessage())
//                                .receivedAt( LocalDateTime.now() )
//                                .status( 400 )
//                                .method( HttpMethod.resolve(req.getMethod()) )
//                                .path( req.getRequestURL().toString() )
//                                .build()
//                                .addInfo("errors", ex.getMessages())
//                );
//    }
//endregion
