package com.darshangmProject.Job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darshangmProject.Job.Job;
import com.darshangmProject.Job.JobRepository;
import com.darshangmProject.Job.JobService;

@Service
public class JobServiceImplementation implements JobService {

	//private List<Job> jobs=new ArrayList<>();
	JobRepository jobRepository;
	private Long nextId=1L;
	
	public JobServiceImplementation(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findALL() {
		
		return jobRepository.findAll();
	}

	@Override
	public void createaJob(Job job) {
		// TODO Auto-generated method stub
		job.setId(nextId++);
		jobRepository.save(job);
	}

	@Override
	public Job getJobByid(Long id) {
		return jobRepository.findById(id).orElse(null);
	}
	public boolean deleteJobById(Long id) {
		//we will use try catch block to handle exception if we didnt get the jo then we will through NOT FOUND exception
		try {
		jobRepository.deleteById(id);
		return true;
		}
		catch(Exception e) {
			return false;
		}
	}


	@Override
	public boolean updateJob(Long id, Job updatedJob) {
//we will use Optional here 
		Optional<Job> jobOptional=jobRepository.findById(id);
		
			if(jobOptional.isPresent()) {
				Job job=jobOptional.get();				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setLocation(updatedJob.getLocation());
				jobRepository.save(job);
				return true;
			}
		
		return false;
	}


}
