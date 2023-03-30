package user;

import cart.ProductCart;
import exceptions.NewIllegalException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {
    private String name;
    private final String email;

    private String password;

    private List<ProductCart> orders = new ArrayList<>();

    private boolean admin = false;

    public User(String name, String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new NewIllegalException("Не корректная почта или пароль");
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

    public List<ProductCart> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductCart> orders) {
        this.orders = merge(this.orders, orders);
    }

    public static<T> List<T> merge(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>();

        list.addAll(list1);
        list.addAll(list2);

        return list;
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
