package com.controller;

import com.cinema.Merch;
import com.cinema.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/merch")
public class MerchController {

    @Autowired
    MerchService merchService;

    @PostMapping("/addMerch")
    public String addMerch(Model model){

        Merch merch = new Merch();
        model.addAttribute(merch);
        return "addMerch";
    }

    @PostMapping("/insertMerch")
    public String insert(@Valid Merch merch, Model model){
       merchService.addMerch(merch);
       List<Merch> list = merchService.getAll();
       model.addAttribute("merchListDate",list);
       model.addAttribute("success","修改成功");
       return "listAllMerch";

    }

    @PostMapping("/getbyMerchId")
    public String getbyMerchId(@RequestParam("merchId")Integer merchId,Model model){
        Merch merch = merchService.getbyMerchId(Integer.valueOf(merchId));
        model.addAttribute("Merch",merch);
        return "getbyMerchId";

    }

    @PostMapping("/updateMerch")
    public String updateMerch(@Valid Merch merch,Model model){

        merchService.updateMerch(merch);
        model.addAttribute("success","修改成功");
        merch = merchService.getbyMerchId(Integer.valueOf(merch.getMerchId()));
        return "getbyMerchId";

    }

    @PostMapping("/deleteMerch")
    public String deleteMerch(@RequestParam("merchId")Integer merchId,Model model){
        merchService.deleteMerch(merchId);
        List<Merch> list = merchService.getAll();
        model.addAttribute("merchListDate",list);
        model.addAttribute("success","刪除成功");
        return "listAllMerch";


    }



}
