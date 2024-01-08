package com.example.shop.service;

import com.example.shop.model.ProductDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
@Service
public class ResultProductFilterWriteDocument {
    @Value("${app.document.path}")
    private String PATH_FILE;

    public void addProductToDocument(List<ProductDto> productDto) {
        final String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss")) + ".xlsx";
        final File file = new File(PATH_FILE);

        try (final Workbook workbook = new XSSFWorkbook()) {
            final Sheet sheet = workbook.createSheet();
            final Row row = sheet.createRow(0);
            final String[] array = Arrays.stream(ProductDto.class.getDeclaredFields()).map(Field::getName).map(Object::toString).toArray(String[]::new);

            for (int i = 0; i < array.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(array[i]);
            }
            int count = 1;
            for (ProductDto product : productDto) {
                Row row1 = sheet.createRow(count++);
                row1.createCell(0).setCellValue(product.getId().toString());
                row1.createCell(1).setCellValue(product.getArticle());
                row1.createCell(2).setCellValue(product.getTitle());
                row1.createCell(3).setCellValue(product.getDescription());
                row1.createCell(4).setCellValue(product.getCategory().name());
                row1.createCell(5).setCellValue(product.getPrice().toString());
                row1.createCell(6).setCellValue(product.getQuantity());
                row1.createCell(7).setCellValue(product.getLastQuantityChange().toString());
                row1.createCell(8).setCellValue(product.getCreateDate().toString());
                row1.createCell(9).setCellValue(product.getIsAvailable());
            }
            if (!file.exists()) {
                file.mkdirs();
                try (final FileOutputStream outputStream = new FileOutputStream(file + "/" + filename)) {
                    workbook.write(outputStream);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


