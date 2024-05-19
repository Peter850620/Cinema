package com.controller;


import com.cinema.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    EmpService empService;
    @Autowired
    JobService jobService;


    @GetMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(@RequestParam(name = "username") Integer empId, @RequestParam(name = "password") String empPassword, Model model, HttpServletResponse response) throws IOException {

        if (empService.login(empId, empPassword)) {
            Cookie loginInfo = new Cookie("loginAlready", "true");
            loginInfo.setPath("/");
            response.addCookie(loginInfo);
            return "redirect:/emp/index";
        }
        return "redirect:/emp/toLogin";
    }

    @GetMapping("index")
    public String index() {
        return "management";
    }

    @PostMapping("/addEmp")
    public String addEmp(Model model) {

        EmpVO empVO = new EmpVO();
        JobVO jobVO = new JobVO();
        model.addAttribute("empVO",empVO);
        model.addAttribute("jobVO",jobVO);
        return "addEmp";

    }

    @GetMapping("/addEmp")
    public String getEmp(Model model) {

        EmpVO empVO = new EmpVO();
        JobVO jobVO = new JobVO();
        model.addAttribute("empVO",empVO);
        model.addAttribute("jobVO",jobVO);
        return "addEmp";

    }

    @PostMapping("/insertEmp")
    public String insertEmp(@Valid EmpVO empVO, Model model) {

        empService.addEmp(empVO);
        List<EmpVO> list = empService.getAll();
        model.addAttribute("empListDate", list);
        model.addAttribute("success", "新增成功");
        return "listAllEmp";

    }

//    @PostMapping("/getbyId")
//    public String getbyId(@RequestParam("empId") Integer empId, Model model) {
//
//        EmpVO empVO = empService.getbyId(Integer.valueOf(empId));
//        model.addAttribute("EmpVO", empVO);
//        return "updateEmp";
//    }

    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("empId") String empId, ModelMap model) {

        // EmpService empSvc = new EmpService();
        EmpVO empVO = empService.getbyId(Integer.valueOf(empId));
        model.addAttribute("empVO", empVO);
        return "updateEmp"; // 查詢完成後轉交update_emp_input.html
    }

    @GetMapping("/toupdateEmp")
    public String updateEmp() {
        return "updateEmp";
    }
    //ModelMap model
    @PostMapping("updateEmp")
    public String update(@Valid EmpVO empVO)  {

        empVO = empService.getbyId(Integer.valueOf(empVO.getEmpId()));
        // EmpService empSvc = new EmpService();
        empService.updateEmp(empVO);
//        model.addAttribute("success", "修改成功");

//        model.addAttribute("empVO", empVO);
        return  "updateEmp"; // 修改成功後轉交listOneEmp.html
    }


    @PostMapping("/deleteEmp")
    public String deleteEmp(@RequestParam("empId") Integer empId, Model model) {
        empService.deleteEmp(Integer.valueOf(empId));
        List<EmpVO> list = empService.getAll();
        model.addAttribute("empListData", list);
        model.addAttribute("success", "刪除成功");
        return "listAllEmp";
    }

//    @PostMapping("HUComposite")
//    public String listAllEmp(HttpServletRequest req, Model model) {
//        Map<String, String[]> map = req.getParameterMap();
//        List<EmpVO> list = empService.getAll();
//        model.addAttribute("empListData", list); // for listAllEmp.html 第85行用
//        return "listAllEmp";
//    }

    /*
     * 第一種作法 Method used to populate the List Data in view. 如 :
     * <form:select path="deptno" id="deptno" items="${deptListData}" itemValue="deptno" itemLabel="dname" />
     */
    @ModelAttribute("jobListData")
    protected List<JobVO> referenceListData() {
        // DeptService deptSvc = new DeptService();
        List<JobVO> list = jobService.getAll();
        return list;
    }

    /*
     * 【 第二種作法 】 Method used to populate the Map Data in view. 如 :
     * <form:select path="deptno" id="deptno" items="${depMapData}" />
     */
    @ModelAttribute("jobMapData") //
    protected Map<Integer, String> referenceMapData() {
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(1, "主管");
        map.put(2, "辦公職員");
        map.put(3, "售票人員");
        map.put(4, "客服人員");
        return map;
    }

    // 去除BindingResult中某個欄位的FieldError紀錄
//    public BindingResult removeFieldError(EmpVO empVO, BindingResult result, String removedFieldname) {
//        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
//                .filter(fieldname -> !fieldname.getField().equals(removedFieldname))
//                .collect(Collectors.toList());
//        result = new BeanPropertyBindingResult(empVO, "empVO");
//        for (FieldError fieldError : errorsListToKeep) {
//            result.addError(fieldError);
//        }
//        return result;
//    }

    /*
     * This method will be called on select_page.html form submission, handling POST request
     */
    @PostMapping("listEmps_ByCompositeQuery")
    public String listAllEmp(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<EmpVO> list = empService.getAll();
        model.addAttribute("empListData", list); // for listAllEmp.html 第85行用
        return "/listAllEmp";
    }

    @ResponseBody
    @GetMapping("applyResetPassword")
    public String applyResetPassword(@RequestParam Integer empId,@RequestParam String email) throws JsonProcessingException, UnsupportedEncodingException {
        return empService.resetPassword(empId, email);
    }

    @GetMapping("resetPasswordLink")
    public String resetPasswordLink(@RequestParam String param, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        //todo 解碼

        //判斷是不是過期
        ObjectMapper objectMapper = new ObjectMapper();
        // 將JSON字符串轉換為Java對象
        Map<String, Object> map = objectMapper.readValue(param, HashMap.class);
        Integer empId = (Integer) map.get("empId");
        Long expired = (Long) map.get("expired");
        Date expiredDate = new Date(expired);
        boolean isExpired = (new Date()).after(expiredDate);
        if (isExpired) {
            //todo 將error取代為設計好的頁面名稱
            return "error";
        }// 將參數添加到重定向URL中
        redirectAttributes.addAttribute("empId", empId);
        redirectAttributes.addAttribute("expired", expired);

        return "redirect:/resetPasswordPage"; // 重定向到resetPasswordPage頁面
    }

    @GetMapping("/resetPasswordPage")
    public String resetPasswordPage(@RequestParam Integer empId, @RequestParam Long expired, Model model) {
        // 在這個方法中，你可以使用 empId 和 Expired 這兩個參數進行相關處理
        // 將需要的數據放入Model中，進行視圖渲染
        model.addAttribute("empId", empId);
        model.addAttribute("expired", expired);
        return "resetPasswordPage"; // 返回 resetPasswordPage 頁面
    }

    @GetMapping("doResetPassword")
    @ResponseBody
    public String doResetPassword(@RequestParam Integer empId, @RequestParam String password, @RequestParam String cPassword) {

        try {
            if (!password.equals(cPassword)) {
                throw new Exception();
            }
            EmpVO empVO = empService.getbyId(empId);
            empVO.setEmpPassword(password);
            empService.updateEmp(empVO);
            return "修改好了";
        } catch (Exception e) {
            return "啊失敗了";
        }
    }

    @GetMapping("/select_page")
    public String select_page(Model model) {
        List<EmpVO> empList = empService.getAll();
        List<JobVO> jobList = jobService.getAll();
        model.addAttribute("empListData", empList);
        model.addAttribute("jobListData", jobList); // 添加jobListData到模型中
        return "select_page";
    }


    @GetMapping("/listAllEmp")
    public String listAllEmp(Model model) {
        List<EmpVO> empList = empService.getAll();
        List<JobVO> jobList = jobService.getAll();
        model.addAttribute("empListData", empList);
        model.addAttribute("jobListData", jobList); // 添加jobListData到模型中
        return "listAllEmp";
    }
    @ModelAttribute("empListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<EmpVO> referenceListData(Model model) {

        List<EmpVO> list = empService.getAll();
        return list;
    }

    @ModelAttribute("jobListData") // for select_page.html 第135行用
    protected List<JobVO> referenceListData_Job(Model model) {
        model.addAttribute("jobVO", new JobVO()); // for select_page.html 第133行用
        return jobService.getAll();
    }



}






