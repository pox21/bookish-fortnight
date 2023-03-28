package parser;

import cart.ProductCart;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.List;

public class GsonToJson {
    public void addToJson(List<ProductCart> products, String filePath) {
        Gson gson = new Gson();
        try (FileWriter fileWrite = new FileWriter(filePath)) {
            gson.toJson(products, fileWrite);

        } catch (Exception e) {
            System.out.println("Parsing error " + e.getMessage());
        }
    }
}
