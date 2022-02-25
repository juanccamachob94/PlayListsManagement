package server.services.uploaders;

import java.io.File;
import server.models.AmazonS3ClientContainer;
import server.helpers.StringHelper;

public class AmazonS3UploaderService {
  private AmazonS3ClientContainer amazonS3ClientContainer;
  private static AmazonS3UploaderService defaultAmazonS3UploaderService = null;
  private static AmazonS3UploaderService consumerAmazonS3UploaderService = null;
  private static AmazonS3UploaderService consumerResultAmazonS3UploaderService = null;

  public static void perform(AmazonS3ClientContainer amazonS3ClientContainer, String inputRoute,
    String outputRoute, String contentType) throws Exception {
    String credentialsKey = amazonS3ClientContainer.getCredentialsKey();
    if(credentialsKey.equals("default"))
      getDefault(amazonS3ClientContainer).upload(inputRoute, outputRoute, contentType);
    else if(credentialsKey.equals("consumer"))
      getConsumer(amazonS3ClientContainer).upload(inputRoute, outputRoute, contentType);
    else if(credentialsKey.equals("consumer_result"))
      getConsumerResult(amazonS3ClientContainer).upload(inputRoute, outputRoute, contentType);
  }

  private static AmazonS3UploaderService getDefault(
    AmazonS3ClientContainer amazonS3ClientContainer) throws Exception {
    if(defaultAmazonS3UploaderService == null)
      defaultAmazonS3UploaderService = new AmazonS3UploaderService(amazonS3ClientContainer);
    return defaultAmazonS3UploaderService;
  }

  private static AmazonS3UploaderService getConsumer(
    AmazonS3ClientContainer amazonS3ClientContainer) throws Exception {
    if(consumerAmazonS3UploaderService == null)
      consumerAmazonS3UploaderService = new AmazonS3UploaderService(amazonS3ClientContainer);
    return consumerAmazonS3UploaderService;
  }

  private static AmazonS3UploaderService getConsumerResult(
    AmazonS3ClientContainer amazonS3ClientContainer) throws Exception {
    if(consumerResultAmazonS3UploaderService == null)
      consumerResultAmazonS3UploaderService = new AmazonS3UploaderService(amazonS3ClientContainer);
    return consumerResultAmazonS3UploaderService;
  }

  private AmazonS3UploaderService(AmazonS3ClientContainer amazonS3ClientContainer)
    throws Exception {
    this.amazonS3ClientContainer = amazonS3ClientContainer;
  }

  public void upload(String inputRoute, String outputRoute, String contentType) throws Exception {
    this.amazonS3ClientContainer
      .putObject(sanitizedOutputRoute(outputRoute), new File(inputRoute), contentType);
  }

  private String sanitizedOutputRoute(String outputRoute) throws Exception {
    String absolutePath = this.amazonS3ClientContainer.getAbsolutePath();
    if(!StringHelper.isEmpty(absolutePath) && outputRoute.startsWith(absolutePath))
      return outputRoute.replaceAll(absolutePath, "");
    return outputRoute.startsWith("/") ? outputRoute.substring(1) : outputRoute;
  }

  public AmazonS3ClientContainer getAmazonS3ClientContainer() {
    return this.amazonS3ClientContainer;
  }
}
