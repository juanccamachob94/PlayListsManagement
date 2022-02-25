package server.services.storages.strategies;

import server.helpers.FileHelper;
import server.services.uploaders.LocalUploaderService;

public class LocalStrategy {
  public static void perform(String inputRoute, String outputRoute, String inputType)
    throws Exception {
    if(inputType.equals("route"))
      performByRoute(inputRoute, outputRoute);
    else if(inputType.equals("content"))
      performByContent(inputRoute, outputRoute);
  }

  private static void performByRoute(String inputRoute, String outputRoute) throws Exception {
    LocalUploaderService.perform(inputRoute, outputRoute);
  }

  private static void performByContent(String inputContent, String outputRoute) throws Exception {
    FileHelper.writeOnFile(outputRoute, inputContent);
  }
}
