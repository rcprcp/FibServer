package com.cottagecoders.fibserver;

import com.cottagecoders.fibserver.model.SaveRestoreUsingFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class TestSaveRestoreUsingFile {

  static private String tempPath;
  private String fileName;


  @BeforeAll
  static void beforeAll() {
    tempPath = System.getProperty("java.io.tmpdir");
  }

  @BeforeEach
  void beforeEach() {
    //create another file name.
    fileName = tempPath + "/" + "FibServer-" + UUID.randomUUID().toString() + ".tmp";
  }

  @AfterEach
  void after() {
    // delete the file we were working with.
    Assertions.assertDoesNotThrow(() -> Files.delete(Paths.get(fileName)));
  }

  @Test
  void TestSaveRestoreUsingFile() throws IOException {
    // should get here with a new name, and no file.
    // it's created in this method.
    try (SaveRestoreUsingFile sr = new SaveRestoreUsingFile(fileName)) {

      Assertions.assertEquals(false, sr.hasData());
      sr.save(0, 1, 2);

      Assertions.assertEquals(true, sr.hasData());
      Assertions.assertEquals(0, sr.getOffset());
      Assertions.assertEquals(1, sr.getCurrent());
      Assertions.assertEquals(2, sr.getPrevious());
    }

    // get here and file exists.
    // create new object that should restore from the file.
    try (SaveRestoreUsingFile sr = new SaveRestoreUsingFile(fileName)) {

      Assertions.assertEquals(true, sr.hasData());
      Assertions.assertEquals(0, sr.getOffset());
      Assertions.assertEquals(1, sr.getCurrent());
      Assertions.assertEquals(2, sr.getPrevious());
    }
  }

}
