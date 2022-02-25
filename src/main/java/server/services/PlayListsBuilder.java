package server.services;

import org.json.simple.JSONObject;

import server.models.DashPlayList;
import server.models.IndexPlayList;

public class PlayListsBuilder {
  public static DashPlayList buildDashPlayList(JSONObject jsonObject) {
    DashPlayList dashPlayList = new DashPlayList();
    JsonToPlayListDataTransfer.perform(jsonObject, dashPlayList);
    return dashPlayList;
  }

  public static IndexPlayList buildIndexPlayList(JSONObject jsonObject) {
    IndexPlayList indexPlayList = new IndexPlayList();
    JsonToPlayListDataTransfer.perform(jsonObject, indexPlayList);
    indexPlayList.setThumbnail_image_url((String) jsonObject.get("thumbnail_image_url"));
    return indexPlayList;
  }
}
