package ua.kpi.iasa.IASA_Organiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e JOIN FETCH e.invited WHERE e.id=?1")
    Optional<Event> findEventAndHumans(UUID id);

}
