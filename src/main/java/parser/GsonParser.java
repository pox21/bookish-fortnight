package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import product.Category;
import product.Product;
import user.User;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GsonParser {

  public static List<Product> parseProducts(String filePath) {

    Gson gson = new Gson();

    try (FileReader reader = new FileReader(filePath)) {
      return gson.fromJson(reader, new TypeToken<ArrayList<Product>>() {
      }.getType());
    } catch (Exception e) {
      System.out.println("Parsing error " + e.getMessage());
    }

    return null;
  }

  public static List<Category> parseCategories(String filePath) {

    Gson gson = new Gson();

    try (FileReader reader = new FileReader(filePath)) {
      return gson.fromJson(reader, new TypeToken<ArrayList<Category>>() {
      }.getType());
    } catch (Exception e) {
      System.out.println("Parsing error " + e.getMessage());
    }

    return null;
  }

  public static List<User> parseUsers(String filePath) {

    Gson gson = new Gson();

    try (FileReader reader = new FileReader(filePath)) {
      return gson.fromJson(reader, new TypeToken<ArrayList<User>>() {
      }.getType());
    } catch (Exception e) {
      System.out.println("Parsing error " + e.getMessage());
    }

    return null;
  }
}
