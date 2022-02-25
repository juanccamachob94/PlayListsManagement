package clients.terminal;

import server.services.TerminalReader;
import server.services.PlayListsBuilder;
import server.models.DashPlayList;
import server.services.PlayListPositionFinder;
import server.services.data.IndexPlayListsManager;
import server.services.data.DashPlayListsLoader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class TerminalPlayListDeleterClient implements TerminalOptionClient {
  @Override
  public void process() throws Exception {
    String playListId = null;
    System.out.print("Id: ");
    playListId = TerminalReader.perform();
    int indexPosition =
      PlayListPositionFinder.perform(IndexPlayListsManager.jsonData(), playListId);
    if(indexPosition != -1) {
      IndexPlayListsManager.deleteOnStations(indexPosition);
      System.out.println("PlayList #" + Integer.toString(indexPosition) + " eliminado.");
    } else
      System.out.println("No se localiza el Id en Index");
  }
}
