package com.example.shop.service;

import com.example.shop.service.annotation.CheckTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.document.path}")
    private String PATH_FILE;
    @Override
    @CheckTime
    public List<String> getFilenames() {
        final File folder = new File(PATH_FILE + "/");
        if (folder.isDirectory() && folder.exists()) {
            final File[] files = folder.listFiles();
            final List<String> listFiles = Arrays.stream(files).filter(file -> file.isFile() && file != null)
                    .map(File::getName).toList();
            return listFiles;
        }
        return List.of();
    }
}
