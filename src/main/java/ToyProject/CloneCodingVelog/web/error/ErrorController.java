package ToyProject.CloneCodingVelog.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice
@ControllerAdvice
public class ErrorController {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalArgumentExHandler(IllegalArgumentException e) {
//        log.error("잘못된 요청 : [{}]", e.getMessage());
//        return new ErrorResult("Bad_request", e.getMessage());
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgExHandler(IllegalArgumentException e) {
        log.error("잘못된 요청 : ", e);
        return "error/4xx";
    }
}
