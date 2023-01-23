package kinchoi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class OCRouteSummaryForStopFeed extends OCFeed {
    public OCRouteSummaryForStopFeed(int stopNo) {
        super(stopNo);
    }

    public ArrayList<Route> fetchRoutes() {

        ArrayList<Route> routesResult = new ArrayList<>();

        try {
            URL url = new URL("https://api.octranspo1.com/v2.0/GetRouteSummaryForStop?" +
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
                routesResult.add(new Route(r.get("RouteNo").asInt(), r.get("RouteHeading").asText()));
            });
        } catch (
                IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }catch (NullPointerException e){
            System.err.println("Invalid input. Please check.");
            System.out.println();
            System.out.println();

        }
        return routesResult;
    }
}

