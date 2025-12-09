package com.example.ems.service;

import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return repo.findById(id);
    }

    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Employee> searchByName(String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }
}
