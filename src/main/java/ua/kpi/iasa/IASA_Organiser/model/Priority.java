package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;

public enum Priority implements Serializable {
    LOW(1, "Low"), MEDIUM(2, "Medium"), HIGH(3, "High");

    private final int priorityNum;
    private final String name;

    Priority(int priority, String name) {
        this.priorityNum = priority;
        this.name = name;
    }

    public int getPriorityNum() {
        return priorityNum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "priority=" + priorityNum +
                ", name='" + name + '\'' +
                '}';
    }
}
