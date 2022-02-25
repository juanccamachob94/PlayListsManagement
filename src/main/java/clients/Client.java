package clients;

import server.services.data.BaseDataLoader;

public class Client {
  public static void main(String args[]) {
    try {
      System.setProperty("http.agent", "Chrome"); // used to unlock the connection
      BaseDataLoader.load();
      TerminalClient.process();
    } catch(Exception exception) {
    	 exception.printStackTrace();
      System.out.println("Ha fallado la aplicación");
    }
  }
}
