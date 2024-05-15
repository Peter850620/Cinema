package com.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class PermissionService {

        @Autowired
        private PermissionRepository permissionRepository;

        public List<PermissionVO> getAllPermissions() {
            return permissionRepository.findAll();
        }

        public void addPermission(PermissionVO permissionVO){
            permissionRepository.save(permissionVO);

        }

        public PermissionVO getdById(PermissionVO.CompositeDetail compositeDetail) {
            return permissionRepository.findById(compositeDetail).orElse(null);
        }

        public void deleteById(PermissionVO.CompositeDetail compositeDetail) {
            permissionRepository.deleteById(compositeDetail);
        }




    }





