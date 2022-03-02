package server.services.data;

import server.services.StorageStrategyServer;
import server.services.PathService;
import server.services.PropertiesService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.factories.StorageClientFactory;
import server.helpers.FileHelper;
import server.services.storages.StorageService;

public class IndexPlayListsManager {
  public static JSONObject jsonData = null;

  @SuppressWarnings("unchecked")
public static JSONObject jsonData() throws Exception {
    if(jsonData == null)
      jsonData = FileHelper.readOrCreateJSONFile(indexAbsoluteUrl(), null);
      if(jsonData.get("stations") == null)
        jsonData.put("stations", new JSONArray());
    return jsonData;
  }

  private static String indexAbsoluteUrl() throws Exception {
    return indexAbsolutePath() + PropertiesService.env("index_file_relative_route");
  }

  public static String indexAbsolutePath() throws Exception {
    return PathService.getAbsolutePath(StorageStrategyServer.indexStorage());
  }

  private static String indexRelativeUrl() throws Exception {
    return PathService.getRelativePath(StorageStrategyServer.indexStorage())
      + PropertiesService.env("index_file_relative_route");
  }

  @SuppressWarnings("unchecked")
public static void addOnStations(JSONObject jsonObject) throws Exception {
    JSONArray jsonArray = (JSONArray) jsonData.get("stations");
    boolean added = false;
    int jsonObjectId = Integer.parseInt((String) jsonObject.get("id"));
    int jsonArraySize = jsonArray.size();
    for(int i = 0; i < jsonArraySize; i++)
      if(Integer.parseInt((String) ((JSONObject) jsonArray.get(i)).get("id")) > jsonObjectId) {
        jsonArray.add(i, jsonObject);
        added = true;
        break;
      }
    if(!added)
      jsonArray.add(jsonArraySize, jsonObject);
    new StorageService(
      StorageClientFactory.getStorageClient(StorageStrategyServer.indexStorage())
    ).perform(jsonData.toJSONString(), indexRelativeUrl(), "content", "application/json");
  }

  @SuppressWarnings("unchecked")
  public static void setOnStations(int id, JSONObject jsonObject) throws Exception {
    JSONArray jsonArray = (JSONArray) jsonData.get("stations");
    jsonArray.set(id, jsonObject);
    new StorageService(
      StorageClientFactory.getStorageClient(StorageStrategyServer.indexStorage())
    ).perform(jsonData.toJSONString(), indexRelativeUrl(), "content", "application/json");
  }

  public static void deleteOnStations(int id) throws Exception {
    JSONArray jsonArray = (JSONArray) jsonData.get("stations");
    jsonArray.remove(id);
    new StorageService(
      StorageClientFactory.getStorageClient(StorageStrategyServer.indexStorage())
    ).perform(jsonData.toJSONString(), indexRelativeUrl(), "content", "application/json");
  }
}
