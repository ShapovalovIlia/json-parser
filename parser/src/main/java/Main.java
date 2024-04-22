import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
            HashMap<String, SimpleDateFormat> carrierMinFlightTime = new HashMap<>();
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
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();

            Collections.sort(prices);
            System.out.println(prices.get(prices.size() / 2) - sumPrices / prices.size());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
