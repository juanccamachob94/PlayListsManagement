package server.services;

public class LocalPropertiesService {
  private static final String LOCAL_PREFIX = "local_";
  private static LocalPropertiesService defaultLocalPropertiesService = null;

  private String rootPath;
  private String relativePath;

  private static LocalPropertiesService getInstance(String key) throws Exception {
    if(key.equals("default"))
      return getDefault();
    return null;
  }

  private static LocalPropertiesService getDefault() throws Exception {
    if(defaultLocalPropertiesService == null)
      defaultLocalPropertiesService = new LocalPropertiesService("default");
    return defaultLocalPropertiesService;
  }

  private LocalPropertiesService(String key) throws Exception {
    this.rootPath = PropertiesService.env(envKey(key, "root_path"));
    this.relativePath = PropertiesService.env(envKey(key, "relative_path"));
  }

  private static String envKey(String key, String metadata) {
    return LOCAL_PREFIX + key + "_" + metadata;
  }

  @SuppressWarnings("static-access")
  public static String getAbsolutePath() throws Exception {
    return getInstance("default").getRootPath() + getInstance("default").getRelativePath();
  }

  @SuppressWarnings("static-access")
  public static String getAbsolutePath(String key) throws Exception {
    return getInstance(key).getRootPath() + getInstance(key).getRelativePath();
  }

  public static String getRelativePath() throws Exception {
    return getInstance("default").relativePath();
  }

  public static String getRelativePath(String key) throws Exception {
    return getInstance(key).relativePath();
  }

  public static String getRootPath() throws Exception {
    return getInstance("default").rootPath();
  }

  public static String getRootPath(String key) throws Exception {
    return getInstance(key).rootPath();
  }

  private String relativePath() {
    return this.relativePath;
  }

  private String rootPath() {
    return this.rootPath;
  }
}
