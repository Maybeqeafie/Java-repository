package ru.maybeqeafie.jpalessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.maybeqeafie.jpalessons.Entity.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal,Integer> {

}
