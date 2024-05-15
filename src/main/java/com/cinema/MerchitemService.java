package com.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchitemService")
public class MerchitemService {

    @Autowired
    MerchitemRepository merchitemRepository;

    public void addMerchitem(Merchitem merchitem){
        merchitemRepository.save(merchitem);
    }

    public void updateMerchitem(Merchitem merchitem){
        merchitemRepository.save(merchitem);
    }

    public void deleteMerchitem(Integer merchSeq){
        if(merchitemRepository.existsById(merchSeq));
        merchitemRepository.deleteById(merchSeq);
    }



}
