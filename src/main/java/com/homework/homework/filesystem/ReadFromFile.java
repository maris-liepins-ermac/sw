package com.homework.homework.filesystem;

import com.homework.homework.filesystem.exceptions.UnableToReadFromFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    public static List<String> readLineByLine(MultipartFile file) throws UnableToReadFromFileException {
        List<String> result = new ArrayList<>();
        try {
            InputStream is = file.getInputStream();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            throw new UnableToReadFromFileException(e.getMessage());
        }

        return result;
    }
}
