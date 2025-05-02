package ru.job4j.devops.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.CalcEvent;

public interface CalcEventRepository extends CrudRepository<CalcEvent, Long> {
}
