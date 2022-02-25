package clients;

import server.services.TerminalReader;
import clients.terminal.TerminalMenuOption;
import clients.terminal.TerminalPlayListDeleterClient;
import clients.terminal.TerminalPlayListSaverClient;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TerminalClient {
  private static final List<TerminalMenuOption> TERMINAL_MENU_OPTIONS = createTerminalMenuOptions();
  private static final Integer BREAK_OPTION = 3;

  public static void process() throws Exception {
	  TerminalMenuOption terminalMenuOption = null;
    do {
      printMenu();
      terminalMenuOption = readTerminalMenuOption();
      if(terminalMenuOption != null)
        terminalMenuOption.process();
    } while(terminalMenuOption == null || terminalMenuOption.getId() != BREAK_OPTION);
  }

  private static void printMenu() {
    System.out.println();
    System.out.println("=== MENÚ ===");
    int totalTerminalMenuOptions = TERMINAL_MENU_OPTIONS.size();
    for(int i = 0; i < totalTerminalMenuOptions; i++)
      System.out.println(TERMINAL_MENU_OPTIONS.get(i).getMessage());
    System.out.println("-----------");
  }

  private static TerminalMenuOption readTerminalMenuOption() {
    try {
      return findTerminalMenuOption(Integer.parseInt(TerminalReader.perform()));
    } catch(Exception exception) {
      return null;
    }
  }

  private static List<TerminalMenuOption> createTerminalMenuOptions() {
    return Collections.unmodifiableList(
      new ArrayList<TerminalMenuOption>() {
    	  private static final long serialVersionUID = 1L;
    	{
        add(new TerminalMenuOption(1, "Crear o actualizar PlayList",
          new TerminalPlayListSaverClient()));
        add(new TerminalMenuOption(2, "Eliminar PlayList",
          new TerminalPlayListDeleterClient()));
        add(new TerminalMenuOption(3, "Salir", null));
        }
    	}
    );
  }

  private static TerminalMenuOption findTerminalMenuOption(Integer option) {
    for(TerminalMenuOption terminalMenuOption : TERMINAL_MENU_OPTIONS)
      if(terminalMenuOption.getId() == option)
        return terminalMenuOption;
    return null;
  }
}
