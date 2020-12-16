package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;
import java.util.Comparator;

public enum Priority implements Serializable, Comparator<Priority> {
    LOW(1, "Low", "LOW"), MEDIUM(2, "Medium", "MEDIUM"), HIGH(3, "High", "HIGH");

    private final int priorityNum;
    private final String displayName;
    private final String name;

    Priority(int priorityNum, String displayName, String name) {
        this.priorityNum = priorityNum;
        this.displayName = displayName;
        this.name = name;
    }

    public int getPriorityNum() {
        return priorityNum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compare(Priority o1, Priority o2) {
        return o1.getPriorityNum() - o2.getPriorityNum();
    }

    @Override
    public String toString() {
        return "Priority{" +
                "priority=" + priorityNum +
                ", name='" + displayName + '\'' +
                '}';
    }
}
