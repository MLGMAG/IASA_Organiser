package ua.kpi.iasa.IASA_Organiser.data;

/**
 * @deprecated It was useful at first lab, but now use {@link FileDataManager} or {@link ListDataManager} instead.
 */
@Deprecated
public interface ArrayDataManager extends GenericDataManager {
    boolean checkChanges();

    void backUpChangeFlag();

}