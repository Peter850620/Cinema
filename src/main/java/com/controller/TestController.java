package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    public Map<String, Object> newMap(String key, Object val, Object... kv) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, val);
        for (int i = 0; i < kv.length; i += 2) {
            map.put(String.valueOf(kv[i]), kv[i + 1]);
        }
        return map;
    }

    @GetMapping(path = "list")
    public String list(Model model) {
        List<Map> list = new ArrayList<>();
        list.add(newMap("user", "yh", "name", "一灰"));
        list.add(newMap("user", "2h", "name", "2灰"));
        list.add(newMap("user", "3h", "name", "3灰"));
        model.addAttribute("list", list);
        return "listAllEmpTest";
    }


}
