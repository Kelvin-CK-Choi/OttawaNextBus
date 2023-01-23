package kinchoi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class OCNextTripsForStopAllRoutesFeed extends OCFeed {

    public OCNextTripsForStopAllRoutesFeed(int stopNo) {
        super(stopNo);
    }

    public ArrayList<Trip> fetchNextTripsAllRoutes() {
        ArrayList<Trip> tripResult = new ArrayList<>();

        try {
            URL url = new URL("https://api.octranspo1.com/v2.0/GetNextTripsForStopAllRoutes?" +
                    "appID=" + appID +
                    "&apiKey=" + apiKey +
                    "&stopNo=" + getStopNo() +
                    "&format=JSON");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode OCFeed = objectMapper.readTree(url);

            JsonNode getRouteSummaryForStopResult = OCFeed.get("GetRouteSummaryForStopResult");
            JsonNode routes = getRouteSummaryForStopResult.get("Routes");
            JsonNode route = routes.get("Route"); // returns array

            route.forEach(r -> {
                JsonNode trips = r.get("Trips");

                if (trips.isArray()) {
                    trips.forEach(t -> {
                        tripResult.add(new Trip(r.get("RouteNo").asInt(),
                                t.get("TripDestination").asText(),
                                t.get("AdjustedScheduleTime").asInt(),
                                new double[]{t.get("Longitude").asDouble(), t.get("Latitude").asDouble()}));
                    });
                } else {
                    tripResult.add(new Trip(r.get("RouteNo").asInt(),
                            trips.get("TripDestination").asText(),
                            trips.get("AdjustedScheduleTime").asInt(),
                            new double[]{trips.get("Longitude").asDouble(), trips.get("Latitude").asDouble()}));
                }
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e){
            System.err.println("Invalid input. Please check.");
            System.out.println();
            System.out.println();
        }
        return tripResult;
    }
}
