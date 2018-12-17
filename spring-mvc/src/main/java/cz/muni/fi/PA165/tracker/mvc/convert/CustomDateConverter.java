package cz.muni.fi.PA165.tracker.mvc.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

import java.time.LocalDate;

public class CustomDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String time) {
        try {
            String[] parsed = time.split("\\.");
            LocalDate localDate = LocalDate.of(Integer.valueOf(parsed[2]),
                    Integer.valueOf(parsed[1]),
                    Integer.valueOf(parsed[0]));
            return localDate;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}