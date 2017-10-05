package my.sample.view;

/**
 * @author m-washio
 */
public class Contact {
  private int id;

  private String name;

  private String email;

  public Contact(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
