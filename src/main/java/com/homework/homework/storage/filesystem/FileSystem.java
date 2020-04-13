package com.homework.homework.storage.filesystem;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {
    private final String storageLocation = "." + File.separator + "storage";

    public FileSystem() {
        Boolean tmpDirectory = new File(storageLocation).mkdirs();
    }

    public void create(String fileName) {
        File file = new File(storageLocation + File.separator + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String fileName, String content) {
        File file = new File(storageLocation + File.separator + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String fileName) {
        String contents = "";
        try {
            contents = new String(Files.readAllBytes(Paths.get(storageLocation + File.separator + fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public Boolean fileExists(String fileName) {
        File file = new File(storageLocation + File.separator + fileName);
        return file.exists();
    }

    public Boolean fileIsEmpty(String fileName) {
        File file = new File(storageLocation + File.separator + fileName);
        return file.length() == 0;
    }

    public void clearFile(String fileName) {
        if (!this.fileExists(fileName)) {
            return;
        }
        try {
            PrintWriter pw = new PrintWriter(storageLocation + File.separator + fileName);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
