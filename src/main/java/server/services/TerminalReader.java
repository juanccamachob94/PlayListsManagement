package server.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalReader {
  private static TerminalReader terminalReader;
  private BufferedReader bufferedReader;

  public static String perform() {
    try {
      return getInstance().readLine();
    } catch(Exception exception) {
      return null;
    }
  }

  private static TerminalReader getInstance() {
    if(terminalReader == null)
      terminalReader = new TerminalReader();
    return terminalReader;
  }

  private TerminalReader() {
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  }

  private String readLine() throws IOException {
    return this.bufferedReader.readLine();
  }
}
