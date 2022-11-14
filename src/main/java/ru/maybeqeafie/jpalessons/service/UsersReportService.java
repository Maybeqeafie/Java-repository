package ru.maybeqeafie.jpalessons.service;

import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Operation;
import ru.maybeqeafie.jpalessons.Entity.UsersReport;
import ru.maybeqeafie.jpalessons.repository.UsersReportRepository;

import java.util.List;

@Service
public class UsersReportService {
    private final UsersReportRepository usersReportRepository;
    public UsersReportService(UsersReportRepository usersReportRepository) {
        this.usersReportRepository = usersReportRepository;
    }

    public List<UsersReport> getAll() {
        return usersReportRepository.findAll();
    }

    public UsersReport getById(int id) {
        return usersReportRepository.findById(id).orElseThrow();
    }

    public UsersReport save(UsersReport usersReport) {
        return usersReportRepository.save(usersReport);
    }

    public void delete(UsersReport usersReport) {
        usersReportRepository.delete(usersReport);
    }

    public void deleteById(int id) {
        usersReportRepository.deleteById(id);
    }
}
