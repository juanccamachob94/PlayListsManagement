package server.services.data;

public class BaseDataLoader {
  public static void load() throws Exception {
    DashPlayListsLoader.jsonData();
    IndexPlayListsManager.jsonData();
  }
}
