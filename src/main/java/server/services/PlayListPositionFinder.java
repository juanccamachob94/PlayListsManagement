package server.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlayListPositionFinder {
  public static int perform(JSONObject jsonData, String playListId) throws Exception {
    JSONArray stationsArray = (JSONArray) jsonData.get("stations");
    if(stationsArray == null)
      return -1;
    int position = 0;
    for(Object playListJsonObject : stationsArray) {
      if(((JSONObject) playListJsonObject).get("id").equals(playListId))
        return position;
      position += 1;
    }
    return -1;
  }
}
