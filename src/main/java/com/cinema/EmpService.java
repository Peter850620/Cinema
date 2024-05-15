package com.cinema;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.HUCompositeQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service("empService")
@Component
public class EmpService {

    @Autowired
    EmpRepository empRepository;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private com.controller.MailService mailService;


    public void addEmp(EmpVO empVO) {
        empRepository.save(empVO);
    }

    public void updateEmp(EmpVO emp) {

        empRepository.save(emp);
    }

    public void deleteEmp(Integer empId) {
        if (empRepository.existsById(empId))
            empRepository.deleteById(empId);
    }

    public EmpVO getbyId(Integer empId) {
        EmpVO emp = new EmpVO();
        emp.setEmpId(empId);

        Optional<EmpVO> optional = empRepository.findById(empId);
        return optional.orElse(null);
    }

    public List<EmpVO> getAll() {
        return empRepository.findAll();
    }

    public List<EmpVO> getAllC(Map<String, String[]> map) {
        return HUCompositeQuery.getAllC(map, sessionFactory.openSession());

    }

    public boolean login(Integer empId, String empPassword) {
        Optional<EmpVO> optionalEmp = empRepository.findById(empId);

        if (optionalEmp.isPresent()) {
            EmpVO emp = optionalEmp.get();

            if (empPassword.equals(emp.getEmpPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;

        }

    }

    public String resetPassword(Integer empId, String email) throws JsonProcessingException, UnsupportedEncodingException {
        //檢查有沒有此員工
        EmpVO emp = empRepository.findByEmpId(empId);
        if (ObjectUtils.isEmpty(emp)) return "你哪位?";
        //製作有時限的連結
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + 10 * 60 * 1000); // 加上十分鐘的毫秒數
        HashMap<String, Object> map = new HashMap<>();
        map.put("empId", empId);
        map.put("expired", expiredDate);
        ObjectMapper objectMapper = new ObjectMapper();
        //物件轉JSON字串
        String json = objectMapper.writeValueAsString(map);
        //todo 加密json
        //轉成url編碼
        String encode = URLEncoder.encode(json, "UTF-8");
        //宣告網址
        String url = "http://localhost:8081/emp/resetPasswordLink?param=" + encode;
        //發重設密碼連結
        mailService.sendMail(email, "重設密碼", url);
        return "請去收信";
    }


    //複合主鍵:empId調用複合查詢,可以查到JobId,JobId可以查到FuncId,帶出員工的功能
    //empId+JobId+tilte+FuncId


}
