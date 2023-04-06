package user;

import cart.ProductCart;
import exceptions.NewIllegalException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

  private String name;
  private final String email;

  private String password;

  private Map<Integer, List<ProductCart>> orders = new HashMap<>();

  private boolean admin = false;

  public User(String name, String email, String password) {
    if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
      throw new NewIllegalException("Некорректная почта или пароль");
    }
    if (name == null || name.isEmpty()) {
      name = "User" + this.hashCode();
    }

    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public Map<Integer, List<ProductCart>> getOrders() {
    return orders;
  }

  public void setOrders(Map<Integer, List<ProductCart>> order) {
    this.orders = order;
  }

  @Override
  public String toString() {
    return "User {" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", orders=" + orders.toString() + "" +
        ", admin=" + admin +
        '}';
  }
}
