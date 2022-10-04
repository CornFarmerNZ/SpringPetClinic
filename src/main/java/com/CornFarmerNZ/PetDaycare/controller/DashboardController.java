package com.CornFarmerNZ.PetDaycare.controller;

import com.CornFarmerNZ.PetDaycare.repository.PetDaycareRepository;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

@Controller
public class DashboardController {


	private final String bucketName;
	@Autowired
	private AmazonS3Client amazonS3Client;

	@Autowired
	private TransferManager transferManager;

	@Autowired
	PetDaycareRepository repository;


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

	@PostMapping("uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println(file.getContentType());

		File fileTemp = new File("src/main/resources/fileTemp.jpg");
		try (OutputStream os = new FileOutputStream(fileTemp)) {
			os.write(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try{
			Thread.sleep(2000);
			transferManager.upload(bucketName, repository.count()-1+".jpg", fileTemp);
		}
		catch (Exception e){
			System.out.println(e);
		}
		return "addPet";
	}

//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
//		modelMap.addAttribute("file", file);
//		System.out.println("model map added");
//		return "fileUploadView";
//	}
//
//	@GetMapping("/fileUploadView")
//	public String fileUploadView(Model model){
//		return "fileUploadView";
//	}

}
