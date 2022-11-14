package ru.maybeqeafie.jpalessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maybeqeafie.jpalessons.Entity.Accounts;
import ru.maybeqeafie.jpalessons.Entity.Operation;
import ru.maybeqeafie.jpalessons.Entity.Users;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Integer> {

}
