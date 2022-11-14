package ru.maybeqeafie.jpalessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maybeqeafie.jpalessons.Entity.Operation;
import ru.maybeqeafie.jpalessons.Entity.UsersReport;

@Repository
public interface UsersReportRepository extends JpaRepository<UsersReport,Integer> {
}
