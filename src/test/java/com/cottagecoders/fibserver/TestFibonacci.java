package com.cottagecoders.fibserver;

import com.cottagecoders.fibserver.model.Fibonacci;
import com.cottagecoders.fibserver.model.FibonacciOverflowException;
import com.cottagecoders.fibserver.model.FibonacciUnderflowException;
import com.cottagecoders.fibserver.model.SaveRestore;
import com.cottagecoders.fibserver.model.SaveRestoreNoCheckpointFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFibonacci {
  SaveRestore sr = null;
  Fibonacci f = null;

  @BeforeEach
  void setup() {
    sr = new SaveRestoreNoCheckpointFile();
    f = new Fibonacci(sr);

  }

  @Test
  void testGetCurrentValue() {

    Assertions.assertEquals(0, f.current());
    Assertions.assertEquals(0, f.current());
  }

  @Test
  void testGetAFewNextValues() {
    SaveRestoreNoCheckpointFile sr = new SaveRestoreNoCheckpointFile();
    Fibonacci f = new Fibonacci(sr);

    Assertions.assertEquals(0, f.current());
    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(2, f.next());
    Assertions.assertEquals(3, f.next());

  }

  @Test
  void testNextPreviousNextPrevious() {
    SaveRestoreNoCheckpointFile sr = new SaveRestoreNoCheckpointFile();
    Fibonacci f = new Fibonacci(sr);

    Assertions.assertEquals(0, f.current());
    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(1, f.current());

    // sequence is 0, 1, 1, 2, 3
    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(1, f.current());

    Assertions.assertEquals(2, f.next());
    Assertions.assertEquals(2, f.current());

    Assertions.assertEquals(1, f.previous());
    Assertions.assertEquals(1, f.current());

    Assertions.assertEquals(2, f.next());
    Assertions.assertEquals(2, f.current());

    Assertions.assertEquals(1, f.previous());
    Assertions.assertEquals(1, f.current());

  }

  @Test
  void testGetCurrentValueAFewTimes() {
    SaveRestoreNoCheckpointFile sr = new SaveRestoreNoCheckpointFile();
    Fibonacci f = new Fibonacci(sr);

    Assertions.assertEquals(0, f.current());

    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(1, f.current());

    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(1, f.current());

    Assertions.assertEquals(2, f.next());
    Assertions.assertEquals(2, f.current());

    Assertions.assertEquals(3, f.next());
    Assertions.assertEquals(3, f.current());

  }


  @Test
  void testUnderflow() {
    SaveRestoreNoCheckpointFile sr = new SaveRestoreNoCheckpointFile();
    Fibonacci f = new Fibonacci(sr);

    Assertions.assertEquals(0, f.current());
    Assertions.assertEquals(1, f.next());
    Assertions.assertEquals(0, f.previous());
    Assertions.assertThrows(FibonacciUnderflowException.class, () -> f.previous());

  }

  @Test
  void testOverflow() {
    SaveRestoreNoCheckpointFile sr = new SaveRestoreNoCheckpointFile();
    Fibonacci f = new Fibonacci(sr);

    Assertions.assertEquals(0, f.current());

    // This magic number (91) is the number of times we can "next" the Fibonacci sequence
    // and stay within a Java long datatype.
    for (int i = 0; i < 92; i++) {
      long temp = f.next();
    }

    // the next() should overflow the 8-byte long.
    Assertions.assertThrows(FibonacciOverflowException.class, () -> f.next());

  }
}
