package com.cinema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface JobRepository extends JpaRepository<JobVO,Integer> {



}
