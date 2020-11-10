package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface Builder {
    void setType(Type type);

    void setId(UUID id);

    void setName(String name);

    void setPlace(Place place);

    void setInvited(Human[] invited);

    void setDate(LocalDate date);

    void setTime(LocalTime time);

    void setPriority(Priority priority);

    void setTags(Tag[] tags);

    void setDuration(LocalTime duration);

    void setLinks(Link[] links);

    Event getResult();

}
