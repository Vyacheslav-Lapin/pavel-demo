package ru.vlapin.demo.paveldemo.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.demo.paveldemo.model.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}
