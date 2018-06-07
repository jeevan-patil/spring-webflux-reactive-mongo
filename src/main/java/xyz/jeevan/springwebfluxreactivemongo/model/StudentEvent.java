package xyz.jeevan.springwebfluxreactivemongo.model;

import java.util.Date;

public class StudentEvent {

  private String name;
  private String location;
  private Date date;

  public StudentEvent(String name, String location, Date date) {
    this.name = name;
    this.location = location;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
