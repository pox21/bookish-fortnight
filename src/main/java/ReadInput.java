import exceptions.MyIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Products.Products.colorRed;
import static Products.Products.colorReset;

public class ReadInput {

    public static String readStringInput(String input, int limit) {
        String response = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (response == null || response.isEmpty()) {
            try {
                System.out.print(input);
                response = br.readLine().trim();
                if (response.isEmpty()) {
                    System.out.println(input + " - не должно быть пустым \uD83D\uDC48");
                    response = br.readLine().trim();
                } else if (limit != 0 && response.length() < limit) {
                    input = "Значение не может быть меньше " + limit + " символов \uD83D\uDE29";
                    response = null;
                }
            } catch (IOException e) {
                System.out.print("Что-то пошло не так... \uD83D\uDE22" + input);
            }
        }
        return response;
    }

    public static int readIntInput(String input, int limit) {
        int response = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (response == 0 || response > limit) {
            try {
                System.out.print(input);
                response = Integer.parseInt(br.readLine().trim());
                if (response < 1 || response > limit) {
                    throw new MyIOException(" ", response);
                }
            } catch (IOException | MyIOException e) {
                System.out.println(colorRed + " Некорректный ввод : " + e.getMessage() + colorReset);
            }  catch (NumberFormatException e) {
                System.out.println(colorRed + " Некорректный ввод : " + colorReset);
            }
        }
        return response;
    }
}
