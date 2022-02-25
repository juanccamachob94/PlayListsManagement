package server.services;

import server.models.AmazonS3ClientContainer;
import server.factories.StorageClientFactory;

public class AmazonS3PropertiesService {
  /**
   * this attribute is an example, its objective is to get amazon s3 client on credentials key
   * context. The storage strategy isn't relevant.
  */
  private static final String ANY_AMAZONS3_STORAGE_STRATEGY = "basics3";

  public static String getRelativePath(String credentialsKey) throws Exception {
    return ((AmazonS3ClientContainer) StorageClientFactory
      .getStorageClient(credentialsKey, ANY_AMAZONS3_STORAGE_STRATEGY)).getRelativePath();
  }

  public static String getRootPath(String credentialsKey) throws Exception {
    return ((AmazonS3ClientContainer) StorageClientFactory
      .getStorageClient(credentialsKey, ANY_AMAZONS3_STORAGE_STRATEGY)).getRootPath();
  }

  public static String getAbsolutePath(String credentialsKey) throws Exception {
    return ((AmazonS3ClientContainer) StorageClientFactory
      .getStorageClient(credentialsKey, ANY_AMAZONS3_STORAGE_STRATEGY)).getAbsolutePath();
  }
}
