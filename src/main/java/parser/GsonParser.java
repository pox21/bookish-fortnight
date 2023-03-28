package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import product.Product;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GsonParser {

    public List<Product> parse(String filePath) {

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Product>>() {}.getType());
        } catch (Exception e) {
            System.out.println("Parsing error " + e.getMessage());
        }

        return null;
    }
}
