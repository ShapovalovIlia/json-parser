import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import entity.Ticket;

public class Main {
    private static int getTimeDifference(String time1, String time2) {
        int hour1 = Integer.parseInt(time1.split(":")[0]);
        int min1 = Integer.parseInt(time1.split(":")[1]);
        int hour2 = Integer.parseInt(time2.split(":")[0]);
        int min2 = Integer.parseInt(time2.split(":")[1]);

        int totalMin1 = hour1 * 60 + min1;
        int totalMin2 = hour2 * 60 + min2;

        return totalMin2 - totalMin1;
    }


    public static void main(String[] args) {
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(args[0]))) {
            reader.beginObject();
            ArrayList<Integer> prices = new ArrayList<>();
            HashMap<String, String> carrierMinFlightTime = new HashMap<>();
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
                            getTimeDifference(carrierMinFlightTime.get(ticket.getCarrier()), ticket.getFlightTime()) < 0) {
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

            for (HashMap.Entry<String, String> entry : carrierMinFlightTime.entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
            }
            System.out.println(prices.get(prices.size() / 2) - sumPrices / prices.size() );
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
