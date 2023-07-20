package com.placementmanagement.Springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementmanagement.Springboot.entity.Certificate;
import com.placementmanagement.Springboot.responses.ApiResponse;
import com.placementmanagement.Springboot.service.CertificateServiceImpl;


@RestController
@RequestMapping("/api/v1")
public class CertificateController {
	
	@Autowired
	CertificateServiceImpl certificateService;
	
	@GetMapping("/")
	public String Home() {
		String message = "\"Message\" : \"You are in home page\" ";
		return  message;
	}
	
	@GetMapping("/test")
	public ApiResponse Temp() {
		System.out.println("controller test");
		return  certificateService.getTestData();
	}
	
	@GetMapping("/certificates")
	public ApiResponse getAllCertificates() {
		return certificateService.getAllCertificates(); 
	}
	
	@GetMapping("/certificates/{id}")
	public ApiResponse  getThisCertificate(@PathVariable("id") int id ) {
		System.out.println("get this mapping " + id);
		return certificateService.getCertificate(id);
	}
	
	@PostMapping("/certificates")
	public ApiResponse createCertificate(@RequestBody Certificate certificate) {
		return  certificateService.createCertificate(certificate);
	}
	
	@PutMapping("/certificates/{id}")
	public ApiResponse deleteThisCertificate(@RequestBody Certificate certificate, @PathVariable("id") int id){
		return certificateService.updateCertificate(certificate, id);
	}
	
	@DeleteMapping("/certificates/{id}")
	public ApiResponse deleteThisCertificate(@PathVariable int id) {
		return  certificateService.deleteCertificate(id);
	}
	
}
