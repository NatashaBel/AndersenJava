package org.example.model.ticket;

public class BusTicket {
  private String ticketClass;

  private String ticketType;

  private String startDate;

  private String price;

  public String getTicketClass() {
    return ticketClass;
  }

  public String getTicketType() {
    return ticketType;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getPrice() {
    return price;
  }

  public void setTicketClass(String ticketClass) {
    this.ticketClass = ticketClass;
  }

  public void setTicketType(String ticketType) {
    this.ticketType = ticketType;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "BusTicket{"
        + "ticketClass='"
        + ticketClass
        + '\''
        + ", ticketType='"
        + ticketType
        + '\''
        + ", startDate='"
        + startDate
        + '\''
        + ", price='"
        + price
        + '\''
        + '}';
  }
}
