package entity;

import java.util.HashMap;

public record Info(HashMap<String, Long> carrierMinFlightTime, int medianAvgPriceDiff) {
}
