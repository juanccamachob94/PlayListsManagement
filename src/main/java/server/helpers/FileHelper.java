package server.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.services.storages.StorageService;
import server.services.uploaders.TemporaryUploaderService;
import server.services.PathService;

public class FileHelper {

  public static JSONObject readOrCreateJSONFile(String fileRoute, StorageService storageService)
    throws Exception {
    if(fileRoute.startsWith("http"))
      return readExternalOrCreateJSONFile(fileRoute, storageService);
    return readInternalOrCreateJSONFile(fileRoute);
  }

  public static boolean existsFileRouteResource(String fileRoute) throws Exception {
    if(fileRoute.startsWith("http"))
      return existsExternalFileRouteResource(fileRoute);
    return existsInternalFileRouteResource(fileRoute);
  }

  private static JSONObject readInternalOrCreateJSONFile(String fileRoute) throws Exception {
    if(existsFileRouteResource(fileRoute)) {
      try {
        return (JSONObject) new JSONParser().parse(new FileReader(fileRoute));
      } catch (ParseException e) {
        return new JSONObject();
      }
    } else {
      File file = new File(fileRoute);
      file.getParentFile().mkdirs();
      file.createNewFile();
    }
    return new JSONObject();
  }

  private static JSONObject readExternalOrCreateJSONFile(String fileRoute,
    StorageService storageService) throws Exception {
    File temporaryFile = null;
    String filename = UrlHelper.name(fileRoute) + "." + UrlHelper.extension(fileRoute);
    if(existsFileRouteResource(fileRoute)) {
      temporaryFile = TemporaryUploaderService.perform(fileRoute, filename);
      JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(temporaryFile));
      temporaryFile.delete();
      return jsonObject;
    } else if(storageService != null)
      storageService.perform("{}",
        fileRoute.replaceAll(PathService.getRootPath(storageService), ""),
        "content", "application/json");
    return new JSONObject();
  }

  private static boolean existsExternalFileRouteResource(String fileRoute) throws Exception {
    return ((HttpURLConnection) new URL(fileRoute).openConnection()).getResponseCode() == 200;
  }

  private static boolean existsInternalFileRouteResource(String fileRoute) {
    return new File(fileRoute).exists();
  }

  @SuppressWarnings("resource")
  public static void writeOnFile(String fileRoute, String content) throws IOException {
    createDirectories(fileRoute);
    FileWriter file = new FileWriter(fileRoute);
    file.write(content);
    file.flush();
  }

  @SuppressWarnings("resource")
  public static void downloadFile(String url, String localFileRoute) throws Exception {
    createDirectories(localFileRoute);
    new FileOutputStream(localFileRoute).getChannel()
      .transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
  }

  public static void transferUrlContentToFile(URL url, File file) throws IOException {
    FileUtils.copyURLToFile(url, file);
  }

  public static void createDirectories(String route) {
    File directory = new File(route.replaceAll(UrlHelper.nameWithExtension(route), ""));
    if(!directory.exists())
      directory.mkdirs();
  }
}
