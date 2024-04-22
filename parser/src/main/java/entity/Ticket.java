package entity;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    @SerializedName("origin")
    private String origin;

    @SerializedName("origin_name")
    private String originName;

    @SerializedName("destination")
    private String destination;

    @SerializedName("destination_name")
    private String destinationName;

    @SerializedName("departure_date")
    private String departureDate;

    @SerializedName("departure_time")
    private String departureTime;

    @SerializedName("arrival_date")
    private String arrivalDate;

    @SerializedName("arrival_time")
    private String arrivalTime;

    @SerializedName("carrier")
    private String carrier;

    @SerializedName("stops")
    private int stops;

    @SerializedName("price")
    private int price;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFlightTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        String departureDateTime = departureDate + " " + departureTime;
        String arrivalDateTime = arrivalDate + " " + arrivalTime;

        try {
            Date departureDateObj = sdf.parse(departureDateTime);
            Date arrivalDateObj = sdf.parse(arrivalDateTime);
            long differenceInTime = arrivalDateObj.getTime() - departureDateObj.getTime();

            long differenceInHours = differenceInTime / (1000 * 60 * 60);
            long differenceInMinutes = (differenceInTime / (1000 * 60)) % 60;

            String formattedHours = String.format("%02d", differenceInHours);
            String formattedMinutes = String.format("%02d", differenceInMinutes);

            return formattedHours + ":" + formattedMinutes;
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return "Ошибка вычисления времени полета";
        }
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