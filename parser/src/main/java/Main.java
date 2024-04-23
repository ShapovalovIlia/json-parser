import entity.Info;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        // if (args.length == 0) {
        //     System.err.println("No path provided");
        //     return;
        // }
        String path = "parser/tickets.json";

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
        
        int size = info.prices().size();        
        float sum = info.prices().stream().mapToInt(Integer::intValue).sum();
        float avg = sum / size;
        if (size % 2 != 0) {
            int leftMedian = info.prices().get(size / 2);
            int rightMedian = info.prices().get(size / 2 + 1);
            System.out.println(
                "If we choose median as left closer element to mid"
                + Math.abs(avg - leftMedian)
            );

            System.out.println(
                "If we choose median as right closer element to mid" 
                + Math.abs(avg - rightMedian)
            );

            System.out.println(
                "If we choose median as average between left and right mid elements"
                + Math.abs(avg - ((float) leftMedian + rightMedian) / 2)
            );

        } else {
            System.out.println(Math.abs(info.prices().get(size / 2) - avg));
        }
    }
}
