
package ua.kpi.iasa.IASA_Organiser.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.List;

public interface FileDataManager extends GenericDataManager {
    void saveAll(List<Event> events);

}
