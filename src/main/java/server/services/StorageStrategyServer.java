package server.services;

public class StorageStrategyServer {
  private static StorageStrategyServer storageStrategyServer;
  private String indexStorage;

  public static StorageStrategyServer getInstance() throws Exception {
    if(storageStrategyServer == null)
      storageStrategyServer = new StorageStrategyServer();
    return storageStrategyServer;
  }

  private StorageStrategyServer() throws Exception {
    this.indexStorage = PropertiesService.env("storage_strategy_default_index");
  }

  public static String indexStorage() throws Exception {
    return getInstance().getDefaultIndexStorage();
  }

  public String getDefaultIndexStorage() {
    return this.indexStorage;
  }

  public static boolean isAmazonS3StorageStrategy(String storageStrategy) {
    return storageStrategy.equals("basics3") || storageStrategy.equals("locals3");
  }
}
