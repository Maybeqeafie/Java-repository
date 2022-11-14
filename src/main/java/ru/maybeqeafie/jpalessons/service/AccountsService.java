package ru.maybeqeafie.jpalessons.service;

import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Accounts;
import ru.maybeqeafie.jpalessons.Entity.Users;
import ru.maybeqeafie.jpalessons.repository.AccountsRepository;
import ru.maybeqeafie.jpalessons.repository.UsersRepository;

import java.util.List;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    public Accounts other = new Accounts();

    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<Accounts> getAll() {
        return accountsRepository.findAll();
    }

    public Accounts getById(int id) {
        return accountsRepository.findById(id).orElseThrow();
    }

    public Accounts getByName (String name) {
        other.setName("");
        return accountsRepository.findFirstByName(name).orElse(other);
    }

    public Accounts save(Accounts accounts) {
        return accountsRepository.save(accounts);
    }

    public void delete(Accounts accounts) {
        accountsRepository.delete(accounts);
    }

    public void deleteById(int id) {
        accountsRepository.deleteById(id);
    }
}
