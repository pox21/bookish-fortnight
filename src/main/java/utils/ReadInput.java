package utils;

import exceptions.MyIOException;
import utils.Colors;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadInput {

  public static String colorRed = Colors.RED.getColor();
  public static String colorReset = Colors.RESET.getColor();

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

  public static String readStringInput(String input, int limit, boolean email) {
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
        if (response != null && email && !response.contains("@")) {
          input = "Email должен содержать символ @ " + " символов \uD83D\uDE29";
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
        System.out.println(
            colorRed + " Некорректный ввод \uD83D\uDE22 " + e.getMessage() + colorReset);
      } catch (NumberFormatException e) {
        System.out.println(colorRed + " Некорректный ввод \uD83D\uDE22 " + colorReset);
      }
    }
    return response;
  }

  public static char readQuestionInput(String input) {
    String err = Colors.RED.getColor() + "Ответ не может быть пустым \uD83E\uDD2C" + Colors.RESET.getColor();
    String question = Colors.CYAN.getColor() + " (y - 'да', любой символ - 'нет'): " + Colors.RESET.getColor();
    String response = "";
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (response.isEmpty()) {
      try {
        System.out.print(input + question);
        response = br.readLine().trim();
        if (response.isEmpty()) {
          System.out.println(err);
        }
      } catch (IOException e) {
        System.out.println(
            colorRed + " Некорректный ввод \uD83D\uDE22 " + e.getMessage() + colorReset);
      }
    }
    return response.toLowerCase().charAt(0);
  }
}
