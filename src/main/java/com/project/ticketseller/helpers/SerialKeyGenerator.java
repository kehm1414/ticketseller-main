package com.project.ticketseller.helpers;

public class SerialKeyGenerator {

  public static void main(String[] args) {
    System.out.println(generate(10));
  }

  public static String generate(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      char c;
      if (i % 2 == 0) {
        c = (char) ((Math.random() * 26) + 97);
      } else {
        c = (char) ((Math.random() * 10) + 48);
      }
      stringBuilder.append(c);
    }
    return stringBuilder.toString();
  }
}
