package ru.maybeqeafie.jpalessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maybeqeafie.jpalessons.Entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findFirstByLogin (String login);
    Optional<Users> findFirstByPassword (String password);
}
