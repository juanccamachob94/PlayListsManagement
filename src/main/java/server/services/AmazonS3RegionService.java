package server.services;

import com.amazonaws.regions.Regions;

public class AmazonS3RegionService {
  public static Regions getRegions() throws Exception {
    String amazonS3Regions = PropertiesService.env("amazons3_regions");
    if(amazonS3Regions == null)
      return Regions.US_EAST_1;
    else if(amazonS3Regions.equals("us_east_1"))
      return Regions.US_EAST_1;
    else if(amazonS3Regions.equals("us_east_2"))
      return Regions.US_EAST_2;
    return Regions.US_EAST_1;
  }
}
