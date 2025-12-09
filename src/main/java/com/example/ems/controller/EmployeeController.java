package com.example.ems.controller;

import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ✅ GET ALL
    @GetMapping
    public List<Employee> all() {
        return service.getAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ ADD (JSON)
    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return service.save(emp);
    }

    // ✅ ADD (Query Param)
    @PostMapping("/add")
    public Employee createParams(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department) {

        return service.save(new Employee(name, email, department));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,
                                           @RequestBody Employee updated) {

        return service.getById(id).map(e -> {
            e.setName(updated.getName());
            e.setEmail(updated.getEmail());
            e.setDepartment(updated.getDepartment());
            service.save(e);
            return ResponseEntity.ok(e);
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.getById(id).map(e -> {
            service.delete(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public List<Employee> search(@RequestParam String q) {
        return service.searchByName(q);
    }
}
