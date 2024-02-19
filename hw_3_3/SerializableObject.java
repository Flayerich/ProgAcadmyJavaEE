package hw_3_3;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Save {}

class SerializableObject {
    @Save
    private String field1;
    @Save
    private int field2;
    @Save
    private double field3;

    public SerializableObject(String field1, int field2, double field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "SerializableObject{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                '}';
    }
}

class Serializer {
    private static final String FILE_PATH = "/Users/flayerich/IdeaProjects/HWoop/HW_pro/src/hw_3_3/serialized_object.txt";

    public static void serialize(Object obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    oos.writeObject(field.get(obj));
                }
            }
            System.out.println("Об'єкт серіалізовано у файл: " + FILE_PATH);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = new SerializableObject("", 0, 0);
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    field.set(obj, ois.readObject());
                }
            }
            System.out.println("Об'єкт десеріалізовано з файлу: " + FILE_PATH);
            return obj;
        } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Main {
    public static void main(String[] args) {
        SerializableObject obj = new SerializableObject("Hello world!", 42, 3.14);
        Serializer.serialize(obj);
        SerializableObject deserializedObj = (SerializableObject) Serializer.deserialize();
        if (deserializedObj != null) {
            System.out.println("Десеріалізований об'єкт: " + deserializedObj);
        }
    }
}
