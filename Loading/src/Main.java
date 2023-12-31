import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {

        // Разархивация сохранений в папку savegames
        openZip("C:/Games/savegames/saves.zip", "C:/Games/savegames");

        // "Загрузка" и вывод сохранения в консоль
        GameProgress progress = openProgress("C:/Games/savegames/save3.dat");
        System.out.println(progress.toString());
    }

    // Метод для разархивации сохранений
    public static void openZip(String from, String to) {
        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream(from))) {
            ZipEntry entry;
            String name;
            int j = 1;
            while ((entry = zin.getNextEntry()) != null) {
                name = to + String.format("/Save%d.dat", j);
                j++;
                FileOutputStream fout = new FileOutputStream(name);
                for (int i = zin.read(); i != -1; i = zin.read()) {
                    fout.write(i);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Метод для "загрузки" сохранения
    public static GameProgress openProgress(String path) {
        GameProgress progress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            progress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return progress;
    }
}