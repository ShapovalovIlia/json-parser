package entity;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    @SerializedName("origin")
    public String origin;

    @SerializedName("origin_name")
    public String originName;

    @SerializedName("destination")
    public String destination;

    @SerializedName("destination_name")
    public String destinationName;

    @SerializedName("departure_date")
    public String departureDate;

    @SerializedName("departure_time")
    public String departureTime;

    @SerializedName("arrival_date")
    public String arrivalDate;

    @SerializedName("arrival_time")
    public String arrivalTime;

    @SerializedName("carrier")
    public String carrier;

    @SerializedName("stops")
    public int stops;

    @SerializedName("price")
    public int price;


    public long getFlightTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        String departureDateTime = departureDate + " " + departureTime;
        String arrivalDateTime = arrivalDate + " " + arrivalTime;
        Date departureDateObj = sdf.parse(departureDateTime);
        Date arrivalDateObj = sdf.parse(arrivalDateTime);
        return arrivalDateObj.getTime() - departureDateObj.getTime();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}