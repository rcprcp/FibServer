package com.cottagecoders.fibserver.controller;

import com.cottagecoders.fibserver.model.Fibonacci;
import com.cottagecoders.fibserver.model.FibonacciOverflowException;
import com.cottagecoders.fibserver.model.FibonacciUnderflowException;
import com.cottagecoders.fibserver.model.SaveRestoreUsingFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class RestEndpoints {

  private static final String FILE_NAME = "./checkpoint.data";
  // ensure correct spelling :)
  private static final String FIBONACCI = "fibonacci";

  private final Fibonacci fibonacci;

  RestEndpoints() {
    fibonacci = new Fibonacci(new SaveRestoreUsingFile(FILE_NAME));
  }

  @GetMapping(path = "/current")
  public Map<String, Long> current() {

    HashMap<String, Long> map = new HashMap<>();
    map.put(FIBONACCI, fibonacci.current());
    return map;

  }

  @GetMapping(path = "/previous")
  public Map<String, Long> previous() throws FibonacciUnderflowException {

    long fib = fibonacci.previous();  // may throw an exception, so do this first.

    HashMap<String, Long> map = new HashMap<>();
    map.put(FIBONACCI, fib);
    return map;
  }

  @GetMapping(path = "/next")
  public Map<String, Long> next() throws FibonacciOverflowException {
    long val = fibonacci.next();  // may throw an exception, so do this first.

    HashMap<String, Long> map = new HashMap<>();
    map.put(FIBONACCI, val);
    return map;
  }
}

