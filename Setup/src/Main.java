import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<File> files = new ArrayList<>(); // Список со всеми файлами
        List<File> directories = new ArrayList<>(); // Список со всеми папками
        StringBuilder log = new StringBuilder(); // Лог с информацией о создании файла или папки

        // Создание основных директорий в памяти
        File src = new File("C:/Games", "src");
        directories.add(src);
        File res = new File("C:/Games", "res");
        directories.add(res);
        File savegames = new File("C:/Games", "savegames");
        directories.add(savegames);
        File temp = new File("C:/Games", "temp");
        directories.add(temp);

        // Создание директорий в папке src в памяти
        File main = new File(src, "main");
        directories.add(main);
        File test = new File(src, "test");
        directories.add(test);

        // Создание java файлов в папке main
        File file1 = new File(main, "Main.java");
        files.add(file1);
        File file2 = new File(main, "Utils.java");
        files.add(file2);

        // Создание директорий в папке res
        File drawables = new File(res, "drawables");
        directories.add(drawables);
        File vectors = new File(res, "vectors");
        directories.add(vectors);
        File icons = new File(res, "icons");
        directories.add(icons);

        // Создание файла temp.txt в папке temp
        File file3 = new File(temp, "temp.txt");
        files.add(file3);

        // Создание всех директорий
        for (File directory : directories) {
            log.append("[").append(LocalDateTime.now()).append("] Папка ").append(directory.getName());
            if (directory.mkdir())
                log.append(" была успешно создана").append("\n");
            else
                log.append(" не была создана").append("\n");
        }

        // Создание всех файлов
        for (File file : files) {
            try {
                log.append("[").append(LocalDateTime.now()).append("] Файл ").append(file.getName());
                if (file.createNewFile())
                    log.append(" был успешно создан").append("\n");
                else
                    log.append(" не был создан").append("\n");
            } catch (IOException e) {
                System.out.println("catch");
                System.out.println(e.getMessage());
            }
        }

        // Запись log'а в файл temp.txt
        try (FileWriter writer = new FileWriter(file3)) {
            writer.write(log.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}