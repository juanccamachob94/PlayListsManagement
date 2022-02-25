package server.helpers;


public class UrlHelper {
  public static String extension(String url) {
    String response = null;
    try {
      response = StringHelper.lastSubString(StringHelper.lastSubString(url, "/"), "\\.");
    } catch(Exception exception) {
    	System.out.println("Imposible obtener la extensión de " + url);
    }
    return response;
  }

  public static String name(String url) {
    String response = null;
    try {
      response = StringHelper.removeSpecialCharacters(
        StringHelper.lastSubString(url, "/").replaceAll("\\.[a-zA-Z0-9]+", "")
      );
    } catch(Exception exception) {
      System.out.println("Imposible obtener el nombre de " + url);
    }
    return response;
  }

  public static String nameWithExtension(String url) {
    return name(url) + "." + extension(url);
  }
}
