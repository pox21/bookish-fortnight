package parser;

import cart.ProductCart;
import com.google.gson.Gson;
import user.User;

import java.io.FileWriter;
import java.util.List;

public class GsonToJson {
    public void addProductsCartToJson(List<ProductCart> products, String filePath) {
        Gson gson = new Gson();
        try (FileWriter fileWrite = new FileWriter(filePath)) {
            gson.toJson(products, fileWrite);

        } catch (Exception e) {
            System.out.println("Parsing error " + e.getMessage());
        }
    }

    public void addUsersToJson(List<User> users, String filePath) {
        Gson gson = new Gson();
        try (FileWriter fileWrite = new FileWriter(filePath)) {

            gson.toJson(users, fileWrite);

        } catch (Exception e) {
            System.out.println("Parsing error " + e.getMessage());
        }
    }

}
