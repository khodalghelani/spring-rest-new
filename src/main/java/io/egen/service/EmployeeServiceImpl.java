package io.egen.service;

import io.egen.entity.Employee;
import io.egen.exception.BadRequestException;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findOne(String id) {
        Employee existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public Employee create(Employee emp) {
        Employee existing = repository.findByEmail(emp.getEmail());
        if (existing != null) {
            throw new BadRequestException("Employee with email " + emp.getEmail() + " already exists.");
        }
        return repository.create(emp);
    }

    @Transactional
    public Employee update(String id, Employee emp) {
        Employee existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " doesn't exist.");
        }
        return repository.update(emp);
    }

    @Transactional
    public void delete(String id) {
        Employee existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " doesn't exist.");
        }
        repository.delete(existing);
    }
}