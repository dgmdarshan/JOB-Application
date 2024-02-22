package com.darshangmProject.Company;

import java.util.List;

public interface  CompanyService {

	List<Company> getAllCompanies();//abstract method 
		
	boolean updateCompany(Company company,Long id);
	void createCompany(Company company);
	boolean deleteCompantById(Long id);
	Company getCompanyById(Long id);

	
}
	