import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import entity.Ticket;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader("C:\\Users\\LEXUS_KREKER\\Desktop\\json-parser\\parser\\tickets.json"))) {
            reader.beginObject();
            ArrayList<Integer> prices = new ArrayList<>();
            HashMap<String, Long> carrierMinFlightTime = new HashMap<>();
            int sumPrices = 0;

            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("tickets")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        JsonElement ticketElement = JsonParser.parseReader(reader);
                        Ticket ticket = gson.fromJson(ticketElement, Ticket.class);
                        prices.add(ticket.getPrice());
                        sumPrices += ticket.getPrice();

                        if (!carrierMinFlightTime.containsKey(ticket.getCarrier()) ||
                            carrierMinFlightTime.get(ticket.getCarrier()) - ticket.getFlightTime() > 0) {
                            carrierMinFlightTime.put(ticket.getCarrier(), ticket.getFlightTime());
                        }
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();

            Collections.sort(prices);

            for (HashMap.Entry<String, Long> entry : carrierMinFlightTime.entrySet()) {
                long differenceInTime = entry.getValue();
                long differenceInHours = differenceInTime / (1000 * 60 * 60);
                long differenceInMinutes = (differenceInTime / (1000 * 60)) % 60;

                String formattedHours = String.format("%02d", differenceInHours);
                String formattedMinutes = String.format("%02d", differenceInMinutes);
                System.out.println("Carrier - " + entry.getKey() +  " | Minimum flight time - " + formattedHours + ":" + formattedMinutes);
            }
            System.out.println("Difference between median and average price: " + (prices.get(prices.size() / 2) - sumPrices / prices.size()));
        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
