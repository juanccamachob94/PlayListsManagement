package server.services.storages.strategies;

import server.helpers.FileHelper;
import server.models.AmazonS3ClientContainer;
import server.services.uploaders.AmazonS3UploaderService;
import server.services.uploaders.LocalUploaderService;

public class LocalS3Strategy {
  public static void perform(AmazonS3ClientContainer amazonS3ClientContainer, String inputRoute,
    String outputRoute, String inputType, String contentType) throws Exception {
    if(inputType.equals("route"))
      performByRoute(amazonS3ClientContainer, inputRoute, outputRoute, contentType);
    else if(inputType.equals("content"))
      performByContent(amazonS3ClientContainer, inputRoute, outputRoute, contentType);
  }

  private static void performByRoute(AmazonS3ClientContainer amazonS3ClientContainer,
    String inputRoute, String outputRoute, String contentType) throws Exception {
    LocalUploaderService.perform(inputRoute, outputRoute);
    AmazonS3UploaderService.perform(amazonS3ClientContainer, outputRoute, outputRoute, contentType);
  }

  private static void performByContent(AmazonS3ClientContainer amazonS3ClientContainer,
    String inputContent, String outputRoute, String contentType) throws Exception {
    FileHelper.writeOnFile(outputRoute, inputContent);
    AmazonS3UploaderService.perform(amazonS3ClientContainer, outputRoute, outputRoute, contentType);
  }
}
