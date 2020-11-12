package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;

public enum Priority implements Serializable {
    LOW(1), MEDIUM(2), HIGH(3);

    private int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
