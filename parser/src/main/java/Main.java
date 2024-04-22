import entity.Info;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        final Parser parser = new Parser();
        if (args[0] == null) {
            System.err.println("Incorrect path");
            return;
        }
        String path = args[0];

        Info info;
        try {
            info = parser.parse(path);
        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
            return;
        }

        for (var entry : info.carrierMinFlightTime().entrySet()) {
            long timeDiff = entry.getValue();
            long hourDiff = timeDiff / (1000 * 60 * 60);
            long minuteDiff = (timeDiff / (1000 * 60)) % 60;

            String formattedHours = String.format("%02d", hourDiff);
            String formattedMinutes = String.format("%02d", minuteDiff);
            System.out.printf(
                    "Carrier - %s | Minimum flight time - %s:%s%n",
                    entry.getKey(),  formattedHours, formattedMinutes
            );
        }

        System.out.println(
            "Difference between median and average price: " + Math.abs(info.medianAvgPriceDiff())
        );
    }
}
