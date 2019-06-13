package gr4.pm.dhbwverwaltung.io;

import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileIO {
    private File file;

    /**
     * Überprüft ob eine Datei bereits existiert
     *
     * @param name
     * @param external
     * @return boolean exisitiert Datei
     */
    public static boolean fileExists(String name, boolean external) {
        if(external) {
            return new File(Environment.getDataDirectory(), name).exists();
        } else {
            return new File(Environment.getExternalStorageDirectory(), name).exists();
        }
    }

    /**
     * Überprüft ob Berechtigung für externer Speicher schreiben erteilt wurde
     *
     * @return boolean
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Überprüft ob Berechtigung für externer Speicher lesen erteilt wurde
     *
     * @return boolean
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Konstruktor für File-Object
     *
     * @param name
     * @param external use external storage
     */
    public FileIO(String name, boolean external) {
        if(external) {
            file = new File(Environment.getDataDirectory(), name);
        } else {
            file = new File(Environment.getExternalStorageDirectory(), name);
        }
    }

    public File getFile() {
        return file;
    }

    /**
     * Schreibt String in File
     *
     * @param stufftowrite
     * @return
     */
    public boolean writeToFile(String stufftowrite) {
        try {
            file.getParentFile().mkdirs();
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(stufftowrite.getBytes());
            stream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Liest String von File
     *
     * @return String
     */
    public String readFromFile() {
        try {
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
