package server.models;

/**
 * Simple model to prevent null pointer exception on local storage strategy
 */
public class LocalClient implements IStorageClient {
  private String credentialsKey;
  private String storageStrategy;

  public LocalClient(String credentialsKey, String storageStrategy) {
    this.credentialsKey = credentialsKey;
    this.storageStrategy = storageStrategy;
  }

  @Override
  public String getCredentialsKey() {
    return this.credentialsKey;
  }

  @Override
  public String getStorageStrategy() {
    return this.storageStrategy;
  }
}
