package com.example.shop.service.document;

import com.example.shop.service.annotation.CheckTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final File FOLDER = new File("src/main/resources/file_after_filter");

    @Override
    @CheckTime
    public List<String> getFilenames() {
        if (FOLDER.isDirectory() && FOLDER.exists()) {
            final File[] files = FOLDER.listFiles();
            final List<String> listFiles = Arrays.stream(files).filter(file -> file.isFile() && file != null)
                    .map(File::getName).toList();
            return listFiles;
        }
        return null;
    }

    @Override
    public File getFile(String fileName) throws FileNotFoundException {
        final File[] files = FOLDER.listFiles();
        Optional<File> getFile = Arrays.stream(files).filter(file -> file.getName().contains(fileName)).findFirst();
        return getFile.orElseThrow(() -> new FileNotFoundException("Файла с таким именем не существует"));
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try (final FileOutputStream outputStream = new FileOutputStream(FOLDER + File.separator + file.getOriginalFilename())) {
            byte[] bytes = file.getBytes();
            outputStream.write(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Файл успешно загружен!";
    }

}
