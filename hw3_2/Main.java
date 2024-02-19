package hw3_2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface SaveTo {
    String path() default "text.txt";
}

@SaveTo(path = "/Users/flayerich/IdeaProjects/HWoop/HW_pro/src/hw3_2/text.txt")
class TextContainer {
    private String text = "Деякий текст";

    public String getText() {
        return text;
    }
}

class Saver {
    public static void save(TextContainer textContainer) {
        Class<?> cls = textContainer.getClass();
        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo annotation = cls.getAnnotation(SaveTo.class);
            String path = annotation.path();
            try (FileWriter fileWriter = new FileWriter(path)) {
                fileWriter.write(textContainer.getText());
                System.out.println("Текст збережено у файлі " + path);
            } catch (IOException e) {
                System.out.println("Помилка при збереженні тексту у файлі");
                e.printStackTrace();
            }
        } else {
            System.out.println("Клас не має анотації SaveTo");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer();
        Saver.save(textContainer);
    }
}
