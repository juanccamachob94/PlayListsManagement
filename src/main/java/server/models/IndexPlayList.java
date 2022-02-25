package server.models;

import org.json.simple.JSONObject;

public class IndexPlayList extends DashPlayList {
  private String thumbnail_image_url;

  public String getThumbnail_image_url() {
    return this.thumbnail_image_url;
  }

  public void setThumbnail_image_url(String thumbnail_image_url) {
    this.thumbnail_image_url = thumbnail_image_url;
  }

  @SuppressWarnings("unchecked")
protected void setJSONDataGroup1(JSONObject jsonObject) {
    super.setJSONDataGroup1(jsonObject);
    jsonObject.put("thumbnail_image_url", this.getThumbnail_image_url());
  }
}
