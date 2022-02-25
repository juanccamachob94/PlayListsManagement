package server.services;

import com.amazonaws.auth.BasicAWSCredentials;

public class AmazonS3CredentialsService {
  private static final String AMAZON_S3_CREDENTIAL_PREFIX = "aws_";
  private BasicAWSCredentials basicAWSCredentials;
  private String bucketName;

  public AmazonS3CredentialsService(String key) throws Exception {
    this.basicAWSCredentials = new BasicAWSCredentials(
      PropertiesService.env(envKey(key, "access_key_id")),
      PropertiesService.env(envKey(key, "secret_key"))
    );
    this.bucketName = PropertiesService.env(envKey(key, "bucket_name"));
  }

  public static String envKey(String key, String metadata) {
    return AMAZON_S3_CREDENTIAL_PREFIX + key + "_" + metadata;
  }

  public BasicAWSCredentials getBasicAWSCredentials() {
    return this.basicAWSCredentials;
  }

  public String getBucketName() {
    return this.bucketName;
  }
}
