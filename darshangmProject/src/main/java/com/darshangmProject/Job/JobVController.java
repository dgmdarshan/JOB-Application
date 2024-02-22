package com.darshangmProject.Job;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darshangmProject.Company.Company;

@RestController

public class JobVController {
	private JobService jobService;
	//Inject at to the controller
	
	
	//GET/jobs :Get all jobs,browse:http://localhost:9090/jobs
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> finadAll(){
		return  ResponseEntity.ok(jobService.findALL());
	}
	
	public JobVController(JobService jobService) {
		this.jobService = jobService;
	}

	//To add new job or create a new job use POST request
	//POST/jobs:To create a new job,to check the request use postman as a request sender
	//in post u cannot use browser use postman
	@PostMapping("/jobs")
	public ResponseEntity<String> createJob(@RequestBody Job job){
		jobService.createaJob(job);
		Company c=job.getCompany();
		return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
	}
	
	
	//
	@GetMapping("/jobs/{id}")//dynamic variable name the can be any number of job id 
	public ResponseEntity<Job>  getJobById(@PathVariable Long id) {
		Job job=jobService.getJobByid(id);
		if(job!=null) {
			return new ResponseEntity<>(job,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//DELETE/jobs/{id}:delete a specific job by id
	//DELETE{http://localhost:9090/jobs/{id}
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted=jobService.deleteJobById(id);
		if(deleted) {
			return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	//PUT/jobs/{id}:Update a specific job by ID
	//PUT{http://localhost:9090/jobs/{id}}
	@PutMapping("/jobs/{id}")
	//@RequestMapping(value="/jobs/{id}",method=RequestMethod.PUT) this one is also applicable in mapping 
	public ResponseEntity<String> updateJob(@PathVariable Long id ,@RequestBody Job updatedJob){
		boolean updated=jobService.updateJob(id,updatedJob);
		if(updated) {
			return  new  ResponseEntity<>("Job updated successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	
}
