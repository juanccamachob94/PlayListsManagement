package server.services.storages;

import server.services.storages.strategies.*;
import server.models.AmazonS3ClientContainer;
import server.models.IStorageClient;
import server.models.LocalClient;

public class StorageService {
  private IStorageClient storageClient;

  public StorageService(String storageStrategy) {
    this.storageClient = new LocalClient("default", storageStrategy);
  }

  public StorageService(String credentialsKey, String storageStrategy) {
    this.storageClient = new LocalClient(credentialsKey, storageStrategy);
  }

  public StorageService(IStorageClient storageClient) {
    this.storageClient = storageClient;
  }

  public void perform(String input, String outputRoute, String inputType) throws Exception {
    perform(input, outputRoute, inputType, null);
  }

  public void perform(String input, String outputRoute, String inputType, String contentType)
    throws Exception {
    if(!inputType.equals("content") && !inputType.equals("route"))
      throw new Exception("Invalid inputType on Storage Service");
    if(this.getStorageStrategy().equals("local"))
      LocalStrategy.perform(input, outputRoute, inputType);
    else if(this.storageClient instanceof AmazonS3ClientContainer) {
      if(this.getStorageStrategy().equals("basics3"))
        BasicS3Strategy.perform((AmazonS3ClientContainer) this.storageClient, input, outputRoute,
          inputType, contentType);
      else if(this.getStorageStrategy().equals("locals3"))
        LocalS3Strategy.perform((AmazonS3ClientContainer) this.storageClient, input, outputRoute,
          inputType, contentType);
    }
  }

  public String getCredentialsKey() {
    return this.storageClient.getCredentialsKey();
  }

  public String getStorageStrategy() {
    return this.storageClient.getStorageStrategy();
  }
}
