package com.cottagecoders.fibserver.model;

/**
 * Save and return the passed in values.  Implementations are free to do this in various ways.
 */
public interface SaveRestore {

  /**
   * Save and return the passed in values.  Implementations are free to do this in various ways.
   * @param offset
   * @param current
   * @param previous
   */
  void save(long offset, long current, long previous);

  /**
   * Return the value of the current saved offset.
   * @return
   */
  long getOffset();


  /**
   * Return the saved current value.
   * @return
   */
  long getCurrent();

  /**
   * Return the saved previous value.
   * @return
   */
  long getPrevious();

  /**
   * Return if we have saved state.
   * @return
   */
  boolean hasData();

}
