package com.darshangmProject.Company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	private CompanyService companyService;
//initialize
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	@GetMapping()//don't need to map again because I have already map the main path in RequestMapping annotation
	public ResponseEntity<List<Company>>  getAllCompanies(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<String>  updateCompany(@PathVariable Long id,@RequestBody Company company){
		
		companyService.updateCompany(company, id);
		return new ResponseEntity<>("Company updated successfully",HttpStatus.OK);
		
		
	}
	@PostMapping()
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity("Comapny added successfully",HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id ) {
		boolean isdeleted=companyService.deleteCompantById(id);
		if(isdeleted) {
			return new ResponseEntity<>("Company Deleted successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Long id){
		Company company=companyService.getCompanyById(id);
		if(company !=null) {
			return new ResponseEntity<>(company,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
