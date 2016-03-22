package org.koko.resource.store.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.concurrent.Executors;

@Configuration
@PropertySource("classpath:aws.properties")
public class S3Config {

    @Value("${aws.credentials.accessKey}")
    private String accessKey;

    @Value("${aws.credentials.secretKey}")
    private String secretKey;

    @Value("${aws.s3.defaultRegion}")
    private String defaultRegion;

    @Value("${aws.s3.transfer.threadCount}")
    private Integer transferThreadCount;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        RetryPolicy retryPolicy = PredefinedRetryPolicies.getDefaultRetryPolicyWithCustomMaxRetries(5);
        clientConfiguration.setRetryPolicy(retryPolicy);
        return clientConfiguration;
    }

    @Bean
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials, clientConfiguration);
        amazonS3Client.setRegion(Region.getRegion(Regions.fromName(defaultRegion)));
        return amazonS3Client;
    }

    @Bean
    public TransferManager transferManager(AmazonS3Client s3Client) {
        return new TransferManager(s3Client, Executors.newFixedThreadPool(transferThreadCount));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
