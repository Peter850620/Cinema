package com.cinema;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Component
public interface EmpRepository extends JpaRepository<EmpVO,Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from emp where emp_id =?", nativeQuery = true)
    void deleteByEmpId(int empId);

    EmpVO findByEmpId(Integer empId);
//    Optional<User> findByEmail(String empEmail);
}
