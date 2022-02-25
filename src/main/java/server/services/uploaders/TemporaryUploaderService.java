package server.services.uploaders;

import java.io.File;
import java.net.URL;
import server.helpers.FileHelper;
import server.helpers.UrlHelper;

public class TemporaryUploaderService {
  private static TemporaryUploaderService temporalUploaderService;

  public static File perform(String inputRoute, String outputRoute) throws Exception {
    return getInstance().process(inputRoute, outputRoute);
  }

  private static TemporaryUploaderService getInstance() {
    if(temporalUploaderService == null)
      temporalUploaderService = new TemporaryUploaderService();
    return temporalUploaderService;
  }

  public File process(String inputRoute, String outputRoute) throws Exception {
    File tempFile = File
      .createTempFile(UrlHelper.name(outputRoute), "." + UrlHelper.extension(outputRoute));
    FileHelper.transferUrlContentToFile(new URL(inputRoute), tempFile);
    return tempFile;
  }
}
