package server.models;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileInputStream;
import server.services.AmazonS3CredentialsService;
import server.services.PropertiesService;
import server.services.AmazonS3RegionService;

public class AmazonS3ClientContainer implements IStorageClient {
  private AmazonS3CredentialsService amazonS3CredentialsService;
  private AmazonS3 amazonS3;
  private String relativePath;
  private String rootPath;
  private String credentialsKey;
  private String storageStrategy;

  public AmazonS3ClientContainer(String credentialsKey, String storageStrategy) throws Exception {
    this(credentialsKey, storageStrategy, AmazonS3RegionService.getRegions());
  }

  public AmazonS3ClientContainer(String credentialsKey, String storageStrategy, Regions regions)
    throws Exception {
    this.amazonS3CredentialsService = new AmazonS3CredentialsService(credentialsKey);
    this.amazonS3 = this.buildAmazonS3(regions);
    this.relativePath = this.buildRelativePath(credentialsKey);
    this.rootPath = this.buildRootPath(credentialsKey);
    this.credentialsKey = credentialsKey;
    this.storageStrategy = storageStrategy;
  }

  public void putObject(String route, File file, String contentType) throws Exception {
  	ObjectMetadata meta = null;
  	if(contentType != null) {
  	  meta = new ObjectMetadata();
  	  meta.setContentType(contentType);
  	}
    /*
     * withCannedAcl(CannedAccessControlList.PublicRead) was added to ensure the uploaded file
     * will be public. Now this code is not required; is a double strategy to prevent some error.
     */
    this.amazonS3.putObject(
      new PutObjectRequest(
      		this.amazonS3CredentialsService.getBucketName(), route, new FileInputStream(file), meta
      ).withCannedAcl(CannedAccessControlList.PublicRead)
    );
  }

  private AmazonS3 buildAmazonS3(Regions regions) {
    return AmazonS3ClientBuilder.standard()
      .withCredentials(
        new AWSStaticCredentialsProvider(this.amazonS3CredentialsService.getBasicAWSCredentials())
      )
      .withRegion(regions).build();
  }

  private String buildRelativePath(String credentialsKey) throws Exception {
    return PropertiesService.env(
      AmazonS3CredentialsService.envKey(credentialsKey, "relative_path")
    );
  }

  private String buildRootPath(String credentialsKey) throws Exception {
    return PropertiesService.env(AmazonS3CredentialsService.envKey(credentialsKey, "root_path"));
  }

  public String getRelativePath() {
    return this.relativePath;
  }

  public String getRootPath() {
    return this.rootPath;
  }

  public String getAbsolutePath() {
    return this.getRootPath() + this.getRelativePath();
  }

  @Override
  public String getCredentialsKey() {
  	return this.credentialsKey;
  }

  @Override
  public String getStorageStrategy() {
    return this.storageStrategy;
  }
}
