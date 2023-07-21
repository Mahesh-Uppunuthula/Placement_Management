package com.placementmanagement.Springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementmanagement.Springboot.entity.Certificate;
import com.placementmanagement.Springboot.repository.CertificateRepository;
import com.placementmanagement.Springboot.responses.ApiResponse;
import com.placementmanagement.Springboot.responses.ApiResponseBuilder;

@Service
public class CertificateServiceImpl implements CertificateServices {

	@Autowired
	CertificateRepository certificateRepo;

	@Override
	public ApiResponse getAllCertificates() {
		// TODO change type to ApiResponse type
		List<Certificate> dataList = certificateRepo.findAll();
		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(dataList, "data found",
				"no content found");
		return builderObj.getResponseInstance();
	}

	@Override
	public ApiResponse getCertificate(int Id) {
		Optional<Certificate> certificateFromDb = certificateRepo.findById(Id);
		System.out.println(certificateFromDb);

		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(certificateFromDb,
				"data found", "no content found");
		return builderObj.getResponseInstance();
	}

	@Override
	public ApiResponse createCertificate(Certificate certificate) {

		Optional<Certificate> newlyCreatedCertificate = Optional.of(certificateRepo.save(certificate));

		// TODO the handle the possible null value of Id.
		String successMsg = "Successfully created Certificate with id : " + newlyCreatedCertificate.get().getId();
		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(newlyCreatedCertificate,
				successMsg, "Failed to create certificate");
		return builderObj.getResponseInstance();
	}

	public ApiResponse deleteCertificate(int id) {

		// TODO handle the IllegalArgumentException
		certificateRepo.deleteById(id);
		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(Optional.of(new Certificate(-1, "no content to show",-1)),
				"certificate deleted successfully " + id, "error occured while deleting");
		return builderObj.getResponseInstance();
	}

	public ApiResponse updateCertificate(Certificate certificate, int id) {
		// TODO validate details before updating
		// TODO change type to ApiResponse type

		System.out.println("id " + id + "\n" + certificate);

		if (isDataValid(certificate)) {
			// Data valid
			Optional<Certificate> certificateFromDb = certificateRepo.findById(id);
			System.out.println("cert from DB " + certificateFromDb);
			certificateFromDb.get().setName(certificate.getName());
			certificateFromDb.get().setYear(certificate.getYear());

			Optional<Certificate> updatedCertificate = Optional.of(certificateRepo.save(certificateFromDb.get()));
			
			ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(updatedCertificate,
					"certificate updated successfully", "error occured while updating certificate");
			System.out.println("builder obj " + builderObj.toString());
			return builderObj.getResponseInstance();
		}
		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(Optional.empty(), "",
				"invalid data");
		return builderObj.getResponseInstance();
	}

	// DATA VALIDATOR
	public boolean isDataValid(Certificate c) {
		boolean isValidName = false;
		boolean isValidYear = false;

		if (!c.equals(null) && !c.getName().trim().isEmpty())
			isValidName = true;

		if (!c.equals(null) && c.getYear() != 0)
			isValidYear = true;

		return isValidName && isValidYear;
	}

	public ApiResponse getTestData() {

		System.out.println("service test");
		List<Certificate> data = certificateRepo.findAll();
//		Optional<Certificate> data = certificateRepo.findById(153);
//		Optional<Certificate> data = certificateRepo.findById(152);
		ApiResponseBuilder<Certificate> builderObj = new ApiResponseBuilder<Certificate>(data, "data found",
				"no content found");
		System.out.println("builder obj " + builderObj.toString());
		return builderObj.getResponseInstance();
	}

}

//

//
