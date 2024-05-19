package com.controller;

import com.cinema.EmpVO;
import com.cinema.JobVO;
import com.cinema.Merch;
import com.cinema.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/merch")
public class MerchController {

    @Autowired
    MerchService merchService;

    @GetMapping("/merchId/{id}")
    public String showProductPage(@PathVariable Integer id, Model model) {
        Merch merch = merchService.getbyMerchId(id);
        model.addAttribute("merch", merch);
        return "TestSingleMerch"; // 返回單獨商品頁面的HTML文件名
    }

    @GetMapping("/front")
    public String front() {
        return "TestMerchStore";
    }

    @GetMapping("/frontsingle")
    public String frontsingle() {
        return "TestSingleMerch";
    }

    @PostMapping("/toggleMerchStatus")
    public String toggleMerchStatus(@Valid Merch merchVo) {

        Merch merch = merchService.getbyMerchId(merchVo.getMerchId());
        String text = "上架";
        if (merch.getMerchStatus().equals("上架")) {
            text = "下架";
        }
        merch.setMerchStatus(text);
        merchService.updateMerch(merch);
        return "redirect:/merch/listAllMerch";
    }


    @PostMapping("/addMerch")
    public String addMerch(Model model) {

        Merch merch = new Merch();
        model.addAttribute(merch);
        return "addMerch";
    }

    @PostMapping("/insertMerch")
    public String insert(@Valid Merch merch, Model model) {
        merchService.addMerch(merch);
        List<Merch> list = merchService.getAll();
        model.addAttribute("merchListDate", list);
        model.addAttribute("success", "修改成功");
        return "listAllMerch";

    }

    @PostMapping("/getbyMerchId")
    public String getbyMerchId(@RequestParam("merchId") Integer merchId, Model model) {
        Merch merch = merchService.getbyMerchId(merchId);
        model.addAttribute("Merch", merch);
        return "TestSingleMerch";

    }

    @PostMapping("/updateMerch")
    public String updateMerch(@Valid Merch merch, Model model) {

        merchService.updateMerch(merch);
        model.addAttribute("success", "修改成功");
        merch = merchService.getbyMerchId(Integer.valueOf(merch.getMerchId()));
        return "getbyMerchId";

    }

    @PostMapping("/deleteMerch")
    public String deleteMerch(@RequestParam("merchId") Integer merchId, Model model) {
        merchService.deleteMerch(merchId);
        List<Merch> list = merchService.getAll();
        model.addAttribute("merchListDate", list);
        model.addAttribute("success", "刪除成功");
        return "listAllMerch";


    }

    @GetMapping("/listAllMerch")
    public String listAllMerch(Model model) {
        List<Merch> merchList = merchService.getAll();
        model.addAttribute("merchListData", merchList);
        return "listAllMerch";
    }

    @ModelAttribute("merchListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<Merch> referenceListData(Model model) {

        List<Merch> list = merchService.getAll();
        return list;
    }

    @PostMapping("/getbyMerchStatus")
    public String MerchStatus(Model model){
        List<Merch> merchlist = merchService.getbyMerchStatus("上架");
        model.addAttribute("merchStatusList",merchlist);
        return "TestMerchStore";
    }


}
