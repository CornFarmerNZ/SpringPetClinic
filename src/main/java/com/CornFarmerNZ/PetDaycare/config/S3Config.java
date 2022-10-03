package com.CornFarmerNZ.PetDaycare.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import software.amazon.awssdk.regions.Region;

@Configuration
public class S3Config {

	@Bean
	public static AmazonS3Client amazonS3Client() {
		return (AmazonS3Client) AmazonS3ClientBuilder
				.standard()
				.withCredentials(new DefaultAWSCredentialsProviderChain())
				.withRegion(Region.US_WEST_2.toString())
				.build();
	}

	@Bean
	public TransferManager transferManager() {
		return (TransferManager)TransferManagerBuilder
				.standard()
				.withS3Client(amazonS3Client())
				.withMultipartUploadThreshold(10000L)
				.build();
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000);
		multipartResolver.setPreserveFilename(true);
		return multipartResolver;
	}


}