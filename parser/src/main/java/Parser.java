import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import entity.Ticket;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Parser {

    public Info parse(String path) throws IOException, ParseException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
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
                    prices.add(ticket.price);
                    sumPrices += ticket.price;

                    if (!carrierMinFlightTime.containsKey(ticket.carrier)
                            || carrierMinFlightTime.get(ticket.carrier) - ticket.getFlightTime() > 0) {
                        carrierMinFlightTime.put(ticket.carrier, ticket.getFlightTime());
                    }
                }
                reader.endArray();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();

        Collections.sort(prices);
        return new Info(carrierMinFlightTime, prices.get(prices.size() / 2) - sumPrices / prices.size());
    }
}
