package com.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncService {

    @Autowired
    FuncRepository repository;

    public void addFunc(FuncVO funcVO){
        repository.save(funcVO);
    }

    public void updataFunc(FuncVO funcVO){
        repository.save(funcVO);
    }

    public void deleteFunc(FuncVO funcVO){
        repository.deleteById(funcVO.getFuncId());
    }

    public FuncVO getById(Integer FuncId){
        Optional<FuncVO> optional = repository.findById(FuncId);
        return optional.orElse(null);

    }

    public List<FuncVO> getAll(FuncVO funcVO){
        return repository.findAll();
    }



}
