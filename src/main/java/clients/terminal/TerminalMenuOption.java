package clients.terminal;

public class TerminalMenuOption {
  private Integer id;
  private String title;
  private TerminalOptionClient terminalOptionClient;

  public TerminalMenuOption(Integer id, String title, TerminalOptionClient terminalOptionClient) {
    this.id = id;
    this.title = title;
    this.terminalOptionClient = terminalOptionClient;
  }

  public void process() throws Exception {
    if(this.terminalOptionClient != null)
      this.terminalOptionClient.process();
  }

  public String getMessage() {
    return Integer.toString(this.id) + ". " + this.title;
  }

  public Integer getId() {
    return this.id;
  }
}
