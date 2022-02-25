package server.services.storages.strategies;

import java.io.File;
import server.helpers.FileHelper;
import server.helpers.UrlHelper;
import server.models.AmazonS3ClientContainer;
import server.services.uploaders.AmazonS3UploaderService;
import server.services.uploaders.TemporaryUploaderService;

public class BasicS3Strategy {
  public static void perform(AmazonS3ClientContainer amazonS3ClientContainer, String input,
    String outputRoute, String inputType, String contentType) throws Exception {
    if(inputType.equals("route"))
      performByRoute(amazonS3ClientContainer, input, outputRoute, contentType);
    else if(inputType.equals("content"))
      performByContent(amazonS3ClientContainer, input, outputRoute, contentType);
  }

  private static void performByRoute(AmazonS3ClientContainer amazonS3ClientContainer,
    String inputRoute, String outputRoute, String contentType) throws Exception {
    File temporaryFile = TemporaryUploaderService.perform(inputRoute, outputRoute);
    AmazonS3UploaderService
      .perform(amazonS3ClientContainer, temporaryFile.getAbsolutePath(), outputRoute, contentType);
    temporaryFile.delete();
  }

  private static void performByContent(AmazonS3ClientContainer amazonS3ClientContainer,
    String inputContent, String outputRoute, String contentType) throws Exception {
    File temporaryFile =
      File.createTempFile(UrlHelper.name(outputRoute), "." + UrlHelper.extension(outputRoute));
    FileHelper.writeOnFile(temporaryFile.getAbsolutePath(), inputContent);
    AmazonS3UploaderService
      .perform(amazonS3ClientContainer, temporaryFile.getAbsolutePath(), outputRoute, contentType);
    temporaryFile.delete();
  }
}
