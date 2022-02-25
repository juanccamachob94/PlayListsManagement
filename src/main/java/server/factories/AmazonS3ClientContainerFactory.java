package server.factories;

import com.amazonaws.regions.Regions;
import server.models.AmazonS3ClientContainer;

public class AmazonS3ClientContainerFactory {
  private static AmazonS3ClientContainer defaultAmazonS3ClientContainer;

  public static AmazonS3ClientContainer getAmazonS3ClientContainer(String credentialsKey,
    String storageStrategy, Regions regions) throws Exception {
    if(credentialsKey.equals("default"))
      return getDefault(storageStrategy, regions);
    return null;
  }

  public static AmazonS3ClientContainer getDefault(String storageStrategy, Regions regions)
    throws Exception {
    if(defaultAmazonS3ClientContainer == null)
      defaultAmazonS3ClientContainer =
        new AmazonS3ClientContainer("default", storageStrategy, regions);
    return defaultAmazonS3ClientContainer;
  }
}
