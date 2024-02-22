package com.darshangmProject.Job;

import java.util.List;

import org.springframework.stereotype.Service;


public interface JobService {
	//We have created interface to promote loose coupling
	List<Job> findALL();
	void createaJob(Job job);
	Job getJobByid(Long id);
	boolean deleteJobById(Long id);
	
	boolean updateJob(Long id, Job updatedJob);
}

