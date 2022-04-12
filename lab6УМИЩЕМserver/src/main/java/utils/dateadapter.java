package utils;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateadapter extends XmlAdapter<String, LocalDateTime>  {
    private DateTimeFormatter CUSTOM_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public String marshal(LocalDateTime dateTime) {
        return dateTime.format(CUSTOM_FORMAT);
    }

    @Override
    public LocalDateTime unmarshal(String dateTime) {
        return LocalDateTime.parse(dateTime, CUSTOM_FORMAT);
    }
}