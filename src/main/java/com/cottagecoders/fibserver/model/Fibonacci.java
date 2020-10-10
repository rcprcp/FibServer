package com.cottagecoders.fibserver.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

  private static final Logger LOG = LoggerFactory.getLogger(Fibonacci.class);

  private final List<Long> fibs = new ArrayList<>();

  private long previous = 0;
  private long current = 1;
  private long offset = 0;

  SaveRestore saveRestore;
  public Fibonacci() {
    saveRestore = new SaveRestore();
    if(saveRestore.hasData()) {
      offset = saveRestore.getOffset();
      current = saveRestore.getCurrent();
      previous = saveRestore.getPrevious();
      LOG.warn("restored offset {}  current {} previous {} ", offset, current, previous);
    }

  }

  public long current() {
    if(offset == 0) {
      return 0;
    } else if(offset == 1) {
      return 1;
    } else {
      return current;
    }
  }

  public long next() throws FibonacciOverflowException {

    offset++;
    // check if we're
    if(offset == 1) {
      return 1;
    }

    long temp;
    try {
      temp = Math.addExact(current, previous);
    } catch(ArithmeticException ex) {
      offset--;    // not moving forward.  so don't increment to next in sequence.
      throw new FibonacciOverflowException();
    }

    previous = current;
    current = temp;

    saveRestore.save(offset, current, previous);
    return current;
  }

  public long previous() {
    // already 0, but trying for previous - should be an error.
    if(offset == 0) {
      throw new FibonacciUnderflowException();  // no number.
    }

    offset--;
    if(offset == 0) {
      return 0;
    } else if(offset == 1){
      return 1;
    }

    long temp = previous;
    previous = current - previous;
    current = temp;

    saveRestore.save(offset, current, previous);
    return current;
  }

}
