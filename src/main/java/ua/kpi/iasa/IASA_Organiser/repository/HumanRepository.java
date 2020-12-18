package ua.kpi.iasa.IASA_Organiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kpi.iasa.IASA_Organiser.model.Human;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HumanRepository extends JpaRepository<Human, UUID> {

    @Query("SELECT h FROM human h JOIN FETCH h.events WHERE h.id=?1")
    Optional<Human> findHumanAndEvents(UUID id);

}
