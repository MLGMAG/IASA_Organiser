package ua.kpi.iasa.IASA_Organiser.data;

public interface ArrayDataManager extends GenericDataManager {
    boolean checkChanges();

    void backUpChangeFlag();

}
