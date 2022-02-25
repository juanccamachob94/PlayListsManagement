package server.services;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import server.models.DashPlayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class JsonToPlayListDataTransfer {
  @SuppressWarnings("unchecked")
public static void perform(JSONObject jsonObject, DashPlayList playList) {
    playList.setId((String) jsonObject.get("id"));
    playList.setName((String) jsonObject.get("name"));
    playList.setGenre((String) jsonObject.get("genre"));
    playList.setGenre_priority((String) jsonObject.get("genre_priority"));
    playList.setStream_url((String) jsonObject.get("stream_url"));
    playList.setClean_stream_url((String) jsonObject.get("clean_stream_url"));
    playList.setRecovery_stream_url((String) jsonObject.get("recovery_stream_url"));
    playList.setCurrent_song_url((String) jsonObject.get("current_song_url"));
    playList.setHistory_feed_url_alt((String) jsonObject.get("history_feed_url_alt"));
    playList.setHistory_feed_url((String) jsonObject.get("history_feed_url"));
    playList.setDefault_cover_url((String) jsonObject.get("default_cover_url"));
    playList.setDescription((String) jsonObject.get("description"));
    playList.setDescription_es((String) jsonObject.get("description_es"));
    playList.setDescription_attributed((String) jsonObject.get("description_attributed"));
    playList.setPrevent_song_cover((String) jsonObject.get("prevent_song_cover"));
    playList.setSlug((String) jsonObject.get("slug"));
    playList.setShort_name((String) jsonObject.get("short_name"));
    playList.setPhone_number((String) jsonObject.get("phone_number"));
    playList.setEnabled((String) jsonObject.get("enabled"));
    playList.setRecovery_enabled((String) jsonObject.get("recovery_enabled"));
    playList.setExplicit_default((String) jsonObject.get("explicit_default"));
    JSONArray jsonTags = (JSONArray) jsonObject.get("tags");
    if(jsonTags != null) {
      List<String> tags = new ArrayList<>();
      Iterator<String> iterator = jsonTags.iterator();
      while(iterator.hasNext())
        tags.add(iterator.next());
      playList.setTags(tags);
    }
  }
}
