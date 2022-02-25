package server.factories;

import com.amazonaws.regions.Regions;
import server.models.IStorageClient;
import server.services.StorageStrategyServer;
import server.models.LocalClient;
import server.services.AmazonS3RegionService;

public class StorageClientFactory {
  public static IStorageClient getStorageClient(String storageStrategy) throws Exception {
    return getStorageClient("default", storageStrategy, AmazonS3RegionService.getRegions());
  }

  public static IStorageClient getStorageClient(String storageStrategy, Regions regions)
    throws Exception {
    return getStorageClient("default", storageStrategy, regions);
  }

  public static IStorageClient getStorageClient(String credentialsKey, String storageStrategy)
    throws Exception {
    return getStorageClient(credentialsKey, storageStrategy, AmazonS3RegionService.getRegions());
  }

  public static IStorageClient getStorageClient(String credentialsKey, String storageStrategy,
    Regions regions) throws Exception {
    if(StorageStrategyServer.isAmazonS3StorageStrategy(storageStrategy))
      return AmazonS3ClientContainerFactory.
        getAmazonS3ClientContainer(credentialsKey, storageStrategy, regions);
    return new LocalClient(credentialsKey, storageStrategy);
  }
}
