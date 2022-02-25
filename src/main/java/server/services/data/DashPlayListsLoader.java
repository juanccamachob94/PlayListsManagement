package server.services.data;

import org.json.simple.JSONObject;
import server.helpers.FileHelper;
import server.services.PropertiesService;

public class DashPlayListsLoader {
  private static JSONObject jsonData = null;

  public static JSONObject jsonData() throws Exception {
    if(jsonData == null)
      jsonData = FileHelper.readOrCreateJSONFile(PropertiesService.env("base_input_url"), null);
    return jsonData;
  }
}
