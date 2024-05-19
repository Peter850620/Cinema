package com.cinema;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MerchRepository extends JpaRepository<Merch,Integer> {



    List<Merch> findByMerchStatus(String merchStatus);

}
