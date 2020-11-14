package ua.kpi.iasa.IASA_Organiser.data.impl;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileDataManager implements FileDataManager {
    private static DefaultFileDataManager instance;
    private File file;
    private List<Event> events;

    public void init(String path) {
        file = getFile(path);
        events = getList();
        if (!file.exists()) {
            try {
                initFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            parseData();
        }
    }

    @Override
    public void save(Event event) {
        List<Event> newEvents = getList();
        newEvents.addAll(events);
        newEvents.add(event);
        saveAll(newEvents);
    }

    @Override
    public void update(Event event) {
        List<Event> allEventsList = getAllEventsList();
        for (Event eventInList : allEventsList) {
            if (eventInList.getId().compareTo(event.getId()) == 0) {
                allEventsList.set(allEventsList.indexOf(eventInList), event);
                saveAll(allEventsList);
                break;
            }
        }
    }

    @Override
    public void remove(Event event) {
        List<Event> currentEvents = getAllEventsList();
        if (currentEvents.remove(event)) {
            saveAll(currentEvents);
        }
    }

    @Override
    public Event[] getAllEvents() {
        throw new UnsupportedOperationException("You are working with FileDataManager!");
    }

    @Override
    public List<Event> getAllEventsList() {
        return events;
    }

    private void parseData() {
        try (ObjectInputStream objectInputStream = getObjectInputStream(file)) {
            while (objectInputStream.available() != -1) {
                events.add((Event) readObjectFromObjectInputStream(objectInputStream));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.getCause(); //TODO: Handle These exception
        }
    }

    @Override
    public void saveAll(List<Event> events) {
        try (ObjectOutputStream objectOutputStream = getObjectOutputStream(file)) {
            for (Event event : events) {
                writeObjectToFile(objectOutputStream, event);
            }
            setEvents(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Object readObjectFromObjectInputStream(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    void writeObjectToFile(ObjectOutputStream objectOutputStream, Event event) throws IOException {
        objectOutputStream.writeObject(event);
    }

    List<Event> getList() {
        return new ArrayList<>();
    }

    ObjectOutputStream getObjectOutputStream(File file) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(file));
    }

    ObjectInputStream getObjectInputStream(File file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }

    File getFile(String path) {
        return new File(path);
    }

    void initFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new IOException("Can't create new file!");
        }
    }

    public static DefaultFileDataManager getInstance() {
        if (instance == null) {
            instance = new DefaultFileDataManager();
            instance.init("/home/andriy/IdeaProjects/IASA_Organiser/src/main/resources/data/test.txt");
        }
        return instance;
    }

    private void setEvents(List<Event> events) {
        this.events = events;
    }
}
