package server.services;

import java.util.Properties;

public class PropertiesService {
  private static final String PROPERTIES_FILE_ROUTE = "config.properties";
  private static PropertiesService propertiesService = null;
  private Properties properties;

  public static String env(String key) throws Exception {
    return getInstance().getPropertiesEnv(key);
  }

  private static PropertiesService getInstance() throws Exception {
    if(propertiesService == null)
      propertiesService = new PropertiesService();
    return propertiesService;
  }

  private PropertiesService() throws Exception {
    this.initProperties();
  }

  private void initProperties() throws Exception {
    this.properties = new Properties();
    this.properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_ROUTE));
  }

  public String getPropertiesEnv(String key) {
    return (String) this.properties.get(key);
  }
}
