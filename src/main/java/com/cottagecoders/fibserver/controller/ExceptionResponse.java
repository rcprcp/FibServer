package com.cottagecoders.fibserver.controller;

import java.util.Map;

public class ExceptionResponse {

  private Integer status;
  private String path;
  private String errorMessage;
  private String timeStamp;

  /**
   * Constructor for the class that will contain all the request data
   * that we will use to build the JSON response for the caller.
   *
   * @param status  - HTTP status code.
   * @param errorAttributes  information to use in th ereturning error JSON response.
   */

  public ExceptionResponse(int status, Map<String, Object> errorAttributes) {
    this.status = status;
    this.path = (String) errorAttributes.get("path");
    this.errorMessage = (String)errorAttributes.get("message");
    this.timeStamp = errorAttributes.get("timestamp").toString();
  }

  public Integer getStatus() {
    return status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public String getPath() {
    return path;
  }

}
