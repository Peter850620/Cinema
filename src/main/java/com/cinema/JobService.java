package com.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("jobsevice")
@Component
public class JobService {
    @Autowired
    JobRepository repository;

    public void addJob(JobVO jobVO){
        repository.save(jobVO);
    }
    public void updateJob(JobVO jobVO){
        repository.save(jobVO);
    }
    public void deleteJob(Integer JobId){
        repository.deleteById(JobId);
    }

    public JobVO getById(Integer JobId){
        Optional<JobVO> optioinal = repository.findById(JobId);
        return optioinal.orElse(null);
    }

    public List<JobVO> getAll(){
        return repository.findAll();
    }

    public Set<EmpVO> getEmpsByJobId(Integer jobId){
        Set<EmpVO> emps = getById(jobId).getEmps();
        return emps;
    }


}
