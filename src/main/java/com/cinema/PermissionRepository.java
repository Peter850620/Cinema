package com.cinema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<PermissionVO, PermissionVO.CompositeDetail>, JpaSpecificationExecutor<PermissionVO> {

    PermissionVO findByFuncIdAndJobId(Integer funcId, Integer jobId);

    void deleteByFuncIdAndJobId(Integer funcId, Integer jobId);
}
