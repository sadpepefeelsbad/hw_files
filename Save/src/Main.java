import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        // Создание 3-х объектов класса GameProgress
        GameProgress progress1 = new GameProgress(100, 0, 1, 0);
        GameProgress progress2 = new GameProgress(80, 5, 2, 3);
        GameProgress progress3 = new GameProgress(94, 10, 3, 26);

        // Создание сохранений в папке savegames
        saveGame("C:/Games/savegames/save1.dat", progress1);
        saveGame("C:/Games/savegames/save2.dat", progress2);
        saveGame("C:/Games/savegames/save3.dat", progress3);

        // Создание списка с сохранениями
        List<String> saves = new ArrayList<>();
        saves.add("C:/Games/savegames/save1.dat");
        saves.add("C:/Games/savegames/save2.dat");
        saves.add("C:/Games/savegames/save3.dat");

        // Архивация сохранений
        zipFiles("C:/Games/savegames/saves.zip", saves);
        deleteFiles(saves);

    }

    // Метод для создания сохранений в папке savegames
    public static void saveGame(String path, GameProgress save) {

        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Метод для архивации сохранений
    public static void zipFiles(String path, List<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < files.size(); i++) {
                FileInputStream fis = new FileInputStream(files.get(i));
                ZipEntry entry = new ZipEntry("packed_saves" + (i + 1) + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
            }
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Удаление остаточных файлов вне архива
    public static void deleteFiles(List<String> files) {
        for (String file : files) {
            File file1 = new File(file);
            file1.delete();
        }
    }

}