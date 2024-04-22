import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String test = "25:15";
        Date testDate = sdf.parse(test);
        System.out.println(testDate);
    }
}
