package com.cottagecoders.fibserver.model;

public class SaveRestoreNoCheckpointFile implements SaveRestore {

  boolean hasData = false;
  private long offset = 0;
  private long current = 1;
  private long previous = 0;

  /**
   * This class is used for testing purposes to give a known state, and
   * will not change the checkpoint file.
   */
  public SaveRestoreNoCheckpointFile() {

  }

  public long getCurrent() {
    return current;
  }

  public long getPrevious() {
    return previous;
  }

  public long getOffset() {
    return offset;
  }

  public boolean hasData() {
    return hasData;
  }

  public void save(long offset, long current, long previous) {
    this.offset = offset;
    this.current = current;
    this.previous = previous;
  }
}
