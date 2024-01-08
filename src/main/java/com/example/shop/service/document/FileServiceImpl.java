package com.example.shop.service.document;

import com.example.shop.service.annotation.CheckTime;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.document.path}")
    private String PATH_FILE;

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

    @Override
    public File getFile(String fileName) throws FileNotFoundException {
        final File FOLDER = new File(PATH_FILE);
        final File[] files = FOLDER.listFiles();
        Optional<File> getFile = Arrays.stream(files).filter(file -> file.getName().contains(fileName)).findFirst();
        return getFile.orElseThrow(() -> new FileNotFoundException("Файла с таким именем не существует"));
    }

    @Override
    public String uploadFile(MultipartFile file) {
        final File FOLDER = new File(PATH_FILE);
        try (final FileOutputStream outputStream = new FileOutputStream(FOLDER + File.separator + file.getOriginalFilename())) {
            byte[] bytes = file.getBytes();
            outputStream.write(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Файл успешно загружен!";
    }

}
