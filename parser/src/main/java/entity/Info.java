package entity;

import java.util.ArrayList;
import java.util.HashMap;

public record Info(HashMap<String, Long> carrierMinFlightTime, ArrayList<Integer> prices) {}
