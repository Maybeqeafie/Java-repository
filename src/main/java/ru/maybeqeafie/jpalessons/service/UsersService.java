package ru.maybeqeafie.jpalessons.service;


import org.springframework.stereotype.Service;
import ru.maybeqeafie.jpalessons.Entity.Users;
import ru.maybeqeafie.jpalessons.repository.UsersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    public Users other = new Users();

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Users getById(int id) {
        return usersRepository.findById(id).orElseThrow();
    }

    public Users getByLogin (String login) {
        other.setLogin("");
        return usersRepository.findFirstByLogin(login).orElse(other);
    }

    public Users getByPassword (String password) {
        other.setPassword("");
        return usersRepository.findFirstByPassword(password).orElse(other);
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public void delete(Users users) {
        usersRepository.delete(users);
    }


}
