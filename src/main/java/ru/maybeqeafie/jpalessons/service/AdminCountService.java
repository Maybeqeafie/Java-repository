package ru.maybeqeafie.jpalessons.service;

import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Accounts;
import ru.maybeqeafie.jpalessons.Entity.AdminCount;
import ru.maybeqeafie.jpalessons.repository.AccountsRepository;
import ru.maybeqeafie.jpalessons.repository.AdminCountRepository;

import java.util.List;

@Service
public class AdminCountService {
    private final AdminCountRepository adminCountRepository;
    public AdminCount other = new AdminCount();

    public AdminCountService(AdminCountRepository adminCountRepository) {
        this.adminCountRepository = adminCountRepository;
    }

    public List<AdminCount> getAll() {
        return adminCountRepository.findAll();
    }

    public AdminCount getById(int id) {
        return adminCountRepository.findById(id).orElseThrow();
    }

    public AdminCount save(AdminCount adminCount) {
        return adminCountRepository.save(adminCount);
    }

    public void delete(AdminCount adminCount) {
        adminCountRepository.delete(adminCount);
    }

    public void deleteById(int id) {
        adminCountRepository.deleteById(id);
    }
}
