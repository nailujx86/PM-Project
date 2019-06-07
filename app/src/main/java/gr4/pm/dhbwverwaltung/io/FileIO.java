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

    public static boolean fileExists(String name, boolean external) {
        if(external) {
            return new File(Environment.getDataDirectory(), name).exists();
        } else {
            return new File(Environment.getExternalStorageDirectory(), name).exists();
        }
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

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

    public boolean writeToFile(String stufftowrite) {
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(stufftowrite.getBytes());
            stream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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
