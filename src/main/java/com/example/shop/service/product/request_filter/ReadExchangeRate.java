package com.example.shop.service.product.request_filter;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class ReadExchangeRate {
    public double getExchangeRate() throws IOException, ParseException {
        String FILE_PATH = "C:\\Users\\user\\Downloads\\Shop_task1\\Shop_task1\\src\\main\\resources\\exchangeRate.json";
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        return (Double) jsonObject.get("exchangeRate");
    }
}
