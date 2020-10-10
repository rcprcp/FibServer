package com.cottagecoders.fibserver.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class SaveRestoreUsingFile implements SaveRestore {

  private static final String EXCEPTION = "Exception: {}";
  private static final String FILE_NAME = "./checkpoint.data";
  private static final String FILE_ERROR_MSG = "{} exists.  Error - no {} permission.";
  private static final Logger LOG = LoggerFactory.getLogger(SaveRestoreUsingFile.class);
  private final File file = new File(FILE_NAME);
  private long offset;
  private long current;
  private long previous;
  private boolean hasData;
  private RandomAccessFile raf;

  private final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 3);

  /**
   * SaveRestoreUsingFile class to save and restore so we can recover from a crash.
   */

  public SaveRestoreUsingFile() {

    hasData = false;

    // check if the file exists; verify permissions.
    if (file.exists()) {
      if (!file.canRead()) {
        LOG.error(FILE_ERROR_MSG, FILE_NAME, "read");
        System.exit(3);

      } else if (!file.canWrite()) {
        LOG.error(FILE_ERROR_MSG, FILE_NAME, "write");
        System.exit(3);
      }

      if (file.length() == 0) {
        //does not have valid contents.
        file.delete();
      } else {
        // if it was deleted, it does not have data.
        hasData = true;
      }
    }

    try {
      // open or create the file...
      raf = new RandomAccessFile(FILE_NAME, "rw");
      // if it previously existed, read the saved data.
      LOG.info("file {} ", FILE_NAME);
      if (hasData) {
        byte[] ba = new byte[Long.BYTES * 3];
        raf.read(ba);

        buffer.position(0);
        buffer.put(ba);

        buffer.position(0);
        offset = buffer.getLong();
        current = buffer.getLong();
        previous = buffer.getLong();
      }

    } catch (IOException ex) {
      LOG.error(EXCEPTION, ex.getMessage(), ex);
      System.exit(3);
    }

  }

  public void save(long offset, long current, long previous) {
    this.offset = offset;
    this.current = current;
    this.previous = previous;

    try {
      buffer.position(0);
      buffer.putLong(offset);
      buffer.putLong(current);
      buffer.putLong(previous);
      byte[] ba = buffer.array();

      raf.seek(0L);
      raf.write(ba);

    } catch (IOException ex) {
      LOG.error(EXCEPTION, ex.getMessage(), ex);
      System.exit(3);
    }
  }

  public boolean hasData() {
    LOG.debug("hasData {}", hasData);
    return hasData;
  }

  public long getOffset() {
    LOG.debug("getOffset() {}", offset);
    return offset;
  }

  public long getCurrent() {
    LOG.debug("getCurrent() {}", current);
    return current;
  }

  public long getPrevious() {
    LOG.debug("getPrevious() {}", previous);
    return previous;
  }
}
