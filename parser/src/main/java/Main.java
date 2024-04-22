import entity.Info;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        final Parser parser = new Parser();
        String path = args[0];

        try {
            Info info = parser.parse(path);

            for (HashMap.Entry<String, Long> entry : info.carrierMinFlightTime().entrySet()) {
                long timeDiff = entry.getValue();
                long hourDiff = timeDiff / (1000 * 60 * 60);
                long minuteDiff = (timeDiff / (1000 * 60)) % 60;

                String formattedHours = String.format("%02d", hourDiff);
                String formattedMinutes = String.format("%02d", minuteDiff);
                System.out.println(
                    "Carrier - " + entry.getKey() + " | Minimum flight time - "
                    + formattedHours + ":" + formattedMinutes
                );
            }

            System.out.println(
                "Difference between median and average price: " + (info.medianAvgPriceDiff())
            );
        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
