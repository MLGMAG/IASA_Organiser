package ua.kpi.iasa.IASA_Organiser.util;

import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class FileUtility {
    private FileUtility() {
    }

    public static void parseData(File file, List<Event> events) {
        try (ObjectInputStream objectInputStream = getObjectInputStream(file)) {
            while (objectInputStream.available() != -1) {
                events.add((Event) readObjectFromObjectInputStream(objectInputStream));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.getCause(); //TODO: Handle These exception
        }
    }

    public static List<Event> saveListToFile(File file, List<Event> events) {
        try (ObjectOutputStream objectOutputStream = getObjectOutputStream(file)) {
            for (Event event : events) {
                writeObjectToFile(objectOutputStream, event);
            }
            return events;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void initFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new IOException("Can't create new file!");
        }
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static Object readObjectFromObjectInputStream(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    public static void writeObjectToFile(ObjectOutputStream objectOutputStream, Event event) throws IOException {
        objectOutputStream.writeObject(event);
    }

    public static ObjectOutputStream getObjectOutputStream(File file) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(file));
    }

    public static ObjectInputStream getObjectInputStream(File file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }
}
