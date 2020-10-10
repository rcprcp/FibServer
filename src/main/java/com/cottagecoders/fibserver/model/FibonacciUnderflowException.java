package com.cottagecoders.fibserver.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request.  No previous number in the Fibonacci sequence.")
public class FibonacciUnderflowException extends RuntimeException {
  public FibonacciUnderflowException() {
    super();
  }

}
