package com.placementmanagement.Springboot.service;



import com.placementmanagement.Springboot.entity.Certificate;
import com.placementmanagement.Springboot.responses.ApiResponse;

public interface CertificateServices {
	ApiResponse getAllCertificates();
	ApiResponse  getCertificate(int Id);
	ApiResponse createCertificate(Certificate certificate);
	ApiResponse updateCertificate(Certificate certificate, int id);
	ApiResponse deleteCertificate(int id);
}
