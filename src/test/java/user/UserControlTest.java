package user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControlTest {

  UsersControl control = new UsersControl();
  // так как фейковые запросы делать мы не умеем, придется даже при запуске тестов добавлять пользователей в "БД"

  @Test
  public void addUser() {
    User user1 = new User("Name", "user" + (int) (Math.random() * 1000) + "@gmail.com", "1234");
    User user2 = new User("", "user" + (int) (Math.random() * 1000) + "@example.de", "123af4");

    System.out.println(user1.getEmail());
    assertTrue(control.addUser(user1));
    assertTrue(control.addUser(user2));
  }

  @Test
  public void getUserByEmail() {
    User user1 = new User("Name", "user" + (int) (Math.random() * 1000) + "@gmail.com", "1234");
    control.addUser(user1);
    assertEquals(user1.getEmail(), control.getUserByEmail(user1.getEmail()).getEmail());
  }

  @Test
  public void login() {
    String email = "user" + (int) (Math.random() * 1000) + "@gmail.com";
    String pass = "1234";
    User user1 = new User("Name", email, pass);
    control.addUser(user1);
    assertEquals(user1.getEmail(), control.login(email, pass).getEmail());
  }


}
