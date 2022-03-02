package clients.terminal;

import server.services.TerminalReader;
import server.services.PlayListsBuilder;
import server.models.DashPlayList;
import server.services.PlayListPositionFinder;
import server.services.data.IndexPlayListsManager;
import server.services.data.DashPlayListsLoader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class TerminalPlayListSaverClient implements TerminalOptionClient {
  @Override
  public void process() throws Exception {
    String playListId = null;
    System.out.print("Id: ");
    playListId = TerminalReader.perform();
    int indexPosition =
      PlayListPositionFinder.perform(IndexPlayListsManager.jsonData(), playListId);
    int dashPosition =
      PlayListPositionFinder.perform(DashPlayListsLoader.jsonData(), playListId);
    if(dashPosition != -1) {
      DashPlayList dashPlayList = PlayListsBuilder.buildDashPlayList(
        findJSONObject(DashPlayListsLoader.jsonData(), dashPosition)
      );
      if(indexPosition != -1)
        updateIndexPlayList(indexPosition, dashPlayList);
      else
        createIndexPlayList(dashPlayList);
    } else
      System.out.println("No se localiza el Id en DASH");
  }

  private void createIndexPlayList(DashPlayList dashPlayList) throws Exception {
    String default_thumbnail_image_url =
      IndexPlayListsManager.indexAbsolutePath() + dashPlayList.getId() + "/thumbnail_image.png";
    IndexPlayListsManager.addOnStations(
      buildPlayListJsonObject(default_thumbnail_image_url, dashPlayList)
    );
    System.out.println("PlayList #" + dashPlayList.getId() + " creado.");
  }

  private void updateIndexPlayList(int indexPosition, DashPlayList dashPlayList) throws Exception {
    JSONObject indexJsonObject = findJSONObject(IndexPlayListsManager.jsonData(), indexPosition);
    String default_thumbnail_image_url = (String) indexJsonObject.get("thumbnail_image_url");
    IndexPlayListsManager.setOnStations(
      indexPosition, buildPlayListJsonObject(default_thumbnail_image_url, dashPlayList)
    );
    System.out.println("PlayList #" + dashPlayList.getId() + " actualizado.");
  }

  private JSONObject buildPlayListJsonObject(String default_thumbnail_image_url,
    DashPlayList dashPlayList) {
    System.out.print("thumbnail_image_url(" + default_thumbnail_image_url + "): ");
    String thumbnail_image_url = TerminalReader.perform();
    if(thumbnail_image_url.equals(""))
      thumbnail_image_url = default_thumbnail_image_url;
    JSONObject dashPlayListJsonObject = dashPlayList.toJSON();
    dashPlayListJsonObject.put("thumbnail_image_url", thumbnail_image_url);
    return PlayListsBuilder.buildIndexPlayList(dashPlayListJsonObject).toJSON();
  }

  private static JSONObject findJSONObject(JSONObject jsonData, int position) {
    return (JSONObject) ((JSONArray) jsonData.get("stations")).get(position);
  }
}
