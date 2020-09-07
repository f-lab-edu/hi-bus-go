package com.younghun.hibusgo.exception;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  @ControllerAdvice : 모든 컨트롤러에서 발생하는 예외를 잡아 처리해주는 어노테이션
 */
@ControllerAdvice
public class ExceptionController {

  /**
   * @valid 유효성 체크에 통과하지 못하면 MethodArgumentNotValidException 발생한다.
   * @param bindingResult
   * @return ResponseEntity<List<ObjectError>>
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ObjectError>> methodValidException(BindingResult bindingResult) {
    return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
  }

}
