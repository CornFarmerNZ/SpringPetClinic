package com.CornFarmerNZ.PetDaycare.controller;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class DashboardController {


	private final String bucketName;
	@Autowired
	private AmazonS3Client amazonS3Client;

	@Autowired
	private TransferManager transferManager;

	@GeneratedValue(strategy = GenerationType.AUTO)
	int photoID;


	private String bucketLocation;

	public DashboardController(
			@Value("petclinicbucket51") String bucketName,
			AmazonS3Client amazonS3Client) {
		this.bucketName = bucketName;
		this.amazonS3Client = amazonS3Client;
	}

	@PostConstruct
	public void postConstruct() {
		this.bucketLocation = String.format("https://%s.s3.%s.amazonaws.com",
				bucketName, this.amazonS3Client.getBucketLocation(bucketName));
	}

	@GetMapping("/aws")
	public ModelAndView getDashboardView() {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		modelAndView.addObject("message", "Spring Boot with AWS");
		modelAndView.addObject("bucketName", bucketName);
		modelAndView.addObject("bucketLocation", bucketLocation);
		modelAndView.addObject("availableFiles", amazonS3Client.listObjects(bucketName).getObjectSummaries());
		return modelAndView;
	}

	@PostMapping("/uploadFile")
	public int uploadFile(@RequestBody byte[] bytes) {
		int id = photoID;
		try{
		File file = new File("./");
		FileOutputStream stream = new FileOutputStream(file);
		stream.write(bytes);
		transferManager.upload(bucketName, ""+photoID, file);
		}
		catch (Exception e){
			System.out.println(e);
		}

		return id;

	}

}
