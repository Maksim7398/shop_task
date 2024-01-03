package com.example.shop.service;

import com.example.shop.service.annotation.CheckTime;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    @CheckTime
    public List<String> getFilenames() {
        final File folder = new File("C:\\Users\\user\\Downloads\\Shop_task1\\Shop_task1\\src\\main\\resources\\file_after_filter");
        if (folder.isDirectory() && folder.exists()) {
            final File[] files = folder.listFiles();
            final List<String> listFiles = Arrays.stream(files).filter(file -> file.isFile() && file != null)
                    .map(File::getName).toList();
            return listFiles;
        }
        return null;
    }
}
