package server.models;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DashPlayList {
  private String id;
  private String name;
  private String genre;
  private String genre_priority;
  private String stream_url;
  private String clean_stream_url;
  private String recovery_stream_url;
  private String current_song_url;
  private String history_feed_url_alt;
  private String history_feed_url;
  private String default_cover_url;
  private String description;
  private String description_es;
  private String description_attributed;
  private String prevent_song_cover;
  private String slug;
  private String short_name;
  private String phone_number;
  private String enabled;
  private String recovery_enabled;
  private String explicit_default;
  private List<String> tags;

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    setJSONDataGroup1(jsonObject);
    setJSONDataGroup2(jsonObject);
    setJSONDataTags(jsonObject);
    return jsonObject;
  }

  @SuppressWarnings("unchecked")
protected void setJSONDataGroup1(JSONObject jsonObject) {
    jsonObject.put("id", this.getId());
    jsonObject.put("name", this.getName());
    jsonObject.put("genre", this.getGenre());
    jsonObject.put("genre_priority", this.getGenre_priority());
    jsonObject.put("stream_url", this.getStream_url());
    jsonObject.put("clean_stream_url", this.getClean_stream_url());
    jsonObject.put("recovery_stream_url", this.getRecovery_stream_url());
    jsonObject.put("current_song_url", this.getCurrent_song_url());
    jsonObject.put("history_feed_url_alt", this.getHistory_feed_url_alt());
    jsonObject.put("history_feed_url", this.getHistory_feed_url());
    jsonObject.put("default_cover_url", this.getDefault_cover_url());
  }

  @SuppressWarnings("unchecked")
protected void setJSONDataGroup2(JSONObject jsonObject) {
    jsonObject.put("description", this.getDescription());
    jsonObject.put("description_es", this.getDescription_es());
    jsonObject.put("description_attributed", this.getDescription_attributed());
    jsonObject.put("prevent_song_cover", this.getPrevent_song_cover());
    jsonObject.put("slug", this.getSlug());
    jsonObject.put("short_name", this.getShort_name());
    jsonObject.put("phone_number", this.getPhone_number());
    jsonObject.put("enabled", this.getEnabled());
    jsonObject.put("recovery_enabled", this.getRecovery_enabled());
    jsonObject.put("explicit_default", this.getExplicit_default());
  }

  @SuppressWarnings("unchecked")
protected void setJSONDataTags(JSONObject jsonObject) {
    if(tags == null)
      return;
    JSONArray jsonTags = new JSONArray();
    for(String tag : this.getTags())
      jsonTags.add(tag);
    jsonObject.put("tags", jsonTags);
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getGenre_priority() {
    return this.genre_priority;
  }

  public void setGenre_priority(String genre_priority) {
    this.genre_priority = genre_priority;
  }

  public String getStream_url() {
    return this.stream_url;
  }

  public void setStream_url(String stream_url) {
    this.stream_url = stream_url;
  }

  public String getClean_stream_url() {
    return this.clean_stream_url;
  }

  public void setClean_stream_url(String clean_stream_url) {
    this.clean_stream_url = clean_stream_url;
  }

  public String getRecovery_stream_url() {
    return this.recovery_stream_url;
  }

  public void setRecovery_stream_url(String recovery_stream_url) {
    this.recovery_stream_url = recovery_stream_url;
  }

  public String getCurrent_song_url() {
    return this.current_song_url;
  }

  public void setCurrent_song_url(String current_song_url) {
    this.current_song_url = current_song_url;
  }

  public String getHistory_feed_url_alt() {
    return this.history_feed_url_alt;
  }

  public void setHistory_feed_url_alt(String history_feed_url_alt) {
    this.history_feed_url_alt = history_feed_url_alt;
  }

  public String getHistory_feed_url() {
    return this.history_feed_url;
  }

  public void setHistory_feed_url(String history_feed_url) {
    this.history_feed_url = history_feed_url;
  }

  public String getDefault_cover_url() {
    return this.default_cover_url;
  }

  public void setDefault_cover_url(String default_cover_url) {
    this.default_cover_url = default_cover_url;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription_es() {
    return this.description_es;
  }

  public void setDescription_es(String description_es) {
    this.description_es = description_es;
  }

  public String getDescription_attributed() {
    return this.description_attributed;
  }

  public void setDescription_attributed(String description_attributed) {
    this.description_attributed = description_attributed;
  }

  public String getPrevent_song_cover() {
    return this.prevent_song_cover;
  }

  public void setPrevent_song_cover(String prevent_song_cover) {
    this.prevent_song_cover = prevent_song_cover;
  }

  public String getSlug() {
    return this.slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getShort_name() {
    return this.short_name;
  }

  public void setShort_name(String short_name) {
    this.short_name = short_name;
  }

  public String getPhone_number() {
    return this.phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getEnabled() {
    return this.enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

  public String getRecovery_enabled() {
    return this.recovery_enabled;
  }

  public void setRecovery_enabled(String recovery_enabled) {
    this.recovery_enabled = recovery_enabled;
  }

  public String getExplicit_default() {
    return this.explicit_default;
  }

  public void setExplicit_default(String explicit_default) {
    this.explicit_default = explicit_default;
  }

  public List<String> getTags() {
    return this.tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
