package com.cottagecoders.fibserver.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request.  Number would overflow Java long datatype.")
public class FibonacciOverflowException extends RuntimeException {
  public FibonacciOverflowException() {
    super();
  }

}
