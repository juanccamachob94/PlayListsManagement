package server.helpers;

public class StringHelper {
  public static String lastSubString(String text, String separator) {
    String array[] = text.split(separator);
    return array[array.length -1];
  }

  public static boolean isEmpty(String str) {
    if(str == null)
      return true;
    return str.trim().isEmpty();
  }

  public static String removeSpecialCharacters(String text) {
    return text.replaceAll("[!@#$%^&*()-/':;,./?><=]", "");
  }
}
