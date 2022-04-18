package ru.maybeqeafie.jpalessons.service;

import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Department;
import ru.maybeqeafie.jpalessons.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(int id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    public Department getByName(String name) {
        return departmentRepository.findFirstByName(name).orElseThrow();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
