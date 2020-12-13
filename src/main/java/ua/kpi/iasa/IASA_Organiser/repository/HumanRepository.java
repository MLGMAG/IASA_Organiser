package ua.kpi.iasa.IASA_Organiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kpi.iasa.IASA_Organiser.model.Human;

import java.util.UUID;

public interface HumanRepository extends JpaRepository<Human, UUID> {

    @Query("select h from human h join fetch h.events where h.id=?1")
    Human getHumanAndEventsById(UUID id);

}
