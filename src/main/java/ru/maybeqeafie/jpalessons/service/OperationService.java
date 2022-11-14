package ru.maybeqeafie.jpalessons.service;

import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Accounts;
import ru.maybeqeafie.jpalessons.Entity.Operation;
import ru.maybeqeafie.jpalessons.repository.AccountsRepository;
import ru.maybeqeafie.jpalessons.repository.OperationRepository;

import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    public Operation getById(int id) {
        return operationRepository.findById(id).orElseThrow();
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public void delete(Operation operation) {
        operationRepository.delete(operation);
    }

    public void deleteById(int id) {
        operationRepository.deleteById(id);
    }
}
