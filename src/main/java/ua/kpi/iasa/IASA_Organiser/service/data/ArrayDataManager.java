package ua.kpi.iasa.IASA_Organiser.service.data;

public interface ArrayDataManager extends GenericDataManager {
    boolean checkChanges();

    void backUpChangeFlag();

}
