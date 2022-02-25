package server.services;

import server.services.storages.StorageService;

public class PathService {
  public static String getAbsolutePath(String storageStrategy) throws Exception {
    return getRootPath(storageStrategy) + getRelativePath(storageStrategy);
  }

  public static String getAbsolutePath(String credentialsKey, String storageStrategy)
    throws Exception {
    return getRootPath(credentialsKey, storageStrategy)
      + getRelativePath(credentialsKey, storageStrategy);
  }

  public static String getAbsolutePath(StorageService storageService) throws Exception {
    return getRootPath(storageService) + getRelativePath(storageService);
  }

  public static String getRootPath(StorageService storageService) throws Exception {
    return getRootPath(storageService.getCredentialsKey(), storageService.getStorageStrategy());
  }

  public static String getRootPath(String storageStrategy) throws Exception {
    return getRootPath("default", storageStrategy);
  }

  public static String getRootPath(String credentialsKey, String storageStrategy)
    throws Exception {
    if(StorageStrategyServer.isAmazonS3StorageStrategy(storageStrategy))
      return AmazonS3PropertiesService.getRootPath(credentialsKey);
    else if(storageStrategy.equals("local"))
      return LocalPropertiesService.getRootPath(credentialsKey);
    return null;
  }

  public static String getRelativePath(String storageStrategy) throws Exception {
    return getRelativePath("default", storageStrategy);
  }

  public static String getRelativePath(StorageService storageService) throws Exception {
    return getRelativePath(storageService.getCredentialsKey(), storageService.getStorageStrategy());
  }

  public static String getRelativePath(String credentialsKey, String storageStrategy)
    throws Exception {
    if(StorageStrategyServer.isAmazonS3StorageStrategy(storageStrategy))
      return AmazonS3PropertiesService.getRelativePath(credentialsKey);
    else if(storageStrategy.equals("local"))
      return LocalPropertiesService.getRelativePath(credentialsKey);
    return null;
  }
}
