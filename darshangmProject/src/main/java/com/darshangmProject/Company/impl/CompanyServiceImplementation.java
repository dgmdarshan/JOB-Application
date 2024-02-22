package com.darshangmProject.Company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darshangmProject.Company.Company;
import com.darshangmProject.Company.CompanyRepository;
import com.darshangmProject.Company.CompanyService;
import com.darshangmProject.Job.Job;

@Service
public class CompanyServiceImplementation implements CompanyService {

	private CompanyRepository companyRepository;
	//initialzie the CompanyRepository
	public CompanyServiceImplementation(CompanyRepository companyrepository) {
		this.companyRepository = companyRepository;
	}
	
	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public boolean updateCompany(Company company, Long id) {
		Optional<Company> companyOptional=companyRepository.findById(id);
		
		if(companyOptional.isPresent()) {
			Company company1=companyOptional.get();		
			company1.setName(company.getName());
			company1.setDescription(company.getDescription());
			company1.setJobs(company.getJobs());
			
	
			companyRepository.save(company1);
			return true;
		}
	
	return false;
		
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompantById(Long id) {
		if(companyRepository.existsById(id)) {
		companyRepository.deleteById(id);
		return true;
		}
		return false;
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	
	

}
