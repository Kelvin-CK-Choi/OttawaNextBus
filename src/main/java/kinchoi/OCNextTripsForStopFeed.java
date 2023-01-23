package kinchoi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class OCNextTripsForStopFeed extends OCFeed {


    public OCNextTripsForStopFeed(int stopNo, int routeNo) {
        super(stopNo, routeNo);
    }

    public ArrayList<Trip> fetchNextTrips() {
        ArrayList<Trip> tripResult = new ArrayList<>();

        try {
            URL url = new URL("https://api.octranspo1.com/v2.0/GetNextTripsForStop?" +
                    "appID=" + appID +
                    "&apiKey=" + apiKey +
                    "&stopNo=" + getStopNo() +
                    "&routeNo=" + getRouteNo() +
                    "&format=JSON");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode OCFeed = objectMapper.readTree(url);

            JsonNode getNextTripsForStopResult = OCFeed.get("GetNextTripsForStopResult");
            JsonNode route = getNextTripsForStopResult.get("Route");
            JsonNode routeDirection = route.get("RouteDirection"); // returns array

            routeDirection.forEach(dir -> {
                JsonNode trips = dir.get("Trips");
                JsonNode trip = trips.get("Trip");
                trip.forEach(t -> {
                    tripResult.add(new Trip(dir.get("RouteNo").asInt(),
                            t.get("TripDestination").asText(),
                            t.get("AdjustedScheduleTime").asInt(),
                            new double[]{t.get("Longitude").asDouble(), t.get("Latitude").asDouble()}));
                });
            });

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Invalid input or this route is currently not in service.");
            System.out.println();
            System.out.println();
        }

        return tripResult;
    }
}








