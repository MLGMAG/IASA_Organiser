package ua.kpi.iasa.IASA_Organiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.iasa.IASA_Organiser.model.Human;

import java.util.UUID;

public interface HumanRepository extends JpaRepository<Human, UUID> {
}
