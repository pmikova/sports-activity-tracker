package cz.muni.fi.PA165.tracker.mvc.convert;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class CustomTimeDateConverter implements Converter<String, LocalDateTime> {


        @Override
        public LocalDateTime convert(String input) {
            try {
                String[] parsed = input.split(" ");
                String date = parsed[0];
                String time = parsed[1];

                String[] dateParsed = date.split("\\.");
                String[] timeParsed = time.split(":");

                LocalDateTime localDate = LocalDateTime.of(Integer.valueOf(dateParsed[2]),
                        Integer.valueOf(dateParsed[1]),
                        Integer.valueOf(dateParsed[0]),
                        Integer.valueOf(timeParsed[0]),
                        Integer.valueOf(timeParsed[1]));
                return localDate;
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }