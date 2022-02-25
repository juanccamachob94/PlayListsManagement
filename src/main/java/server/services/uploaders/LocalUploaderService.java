package server.services.uploaders;

import server.helpers.FileHelper;

public class LocalUploaderService {
  public static void perform(String inputRoute, String outputRoute) throws Exception {
    FileHelper.downloadFile(inputRoute, outputRoute);
  }
}
