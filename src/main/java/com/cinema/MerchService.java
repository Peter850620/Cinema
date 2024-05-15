package com.cinema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("merchService")
@Component
public class MerchService {

    @Autowired
    MerchRepository merchRepository;

    public void addMerch(Merch merch){
        merchRepository.save(merch);
    }

    public void updateMerch(Merch merch){
        merchRepository.save(merch);
    }

    public void deleteMerch(Integer merchId){
        if(merchRepository.existsById(merchId));
        merchRepository.deleteById(merchId);
    }

    public Merch getbyMerchId(Integer merchId){
        Optional<Merch> optional = merchRepository.findById(merchId);
        return optional.orElse(null);

    }

    public List<Merch> getAll(){
        return merchRepository.findAll();
    }



}
