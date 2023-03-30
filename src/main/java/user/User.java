package user;

import exceptions.NewIllegalException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class User {
    private String name;
    private final String email;

    private String password;

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

    @Override
    public String toString() {
        return "User {" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
