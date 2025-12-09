package com.example.ems.controller;

import com.example.ems.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin
public class AnalyticsController {

    private final EmployeeService service;

    public AnalyticsController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public HashMap<String, Object> analytics() {

        HashMap<String, Object> map = new HashMap<>();

        int total = service.getAll().size();

        map.put("totalEmployees", total);
        map.put("activeEmployees", total - 3);
        map.put("onLeave", 2);
        map.put("resigned", 1);

        map.put("leaveApplied", 5);
        map.put("leaveApproved", 3);
        map.put("leavePending", 1);
        map.put("leaveRejected", 1);

        return map;
    }
}
