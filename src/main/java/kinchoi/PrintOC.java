package kinchoi;

import java.util.List;

/**
 * The PrintOC class is a utility class that is used for printing the routes and trips information.
 *
 * It has two methods:
 * printRoutes(List<Route> routes): Prints the routes information in the following format: "Route / Route Heading".
 * printTrips(List<Trip> trips): Prints the trips information in the following format: "Route / Next / Status / Route Heading" and highlights the trips which are less than 10 minutes away in red.
 *
 * @author kinchoi
 */
public class PrintOC {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void printRoutes(List<Route> routes) {
        System.out.println("Route     Route Heading");
        System.out.println("-----------------------------------");

        for (Route route : routes) {
            System.out.printf("%-10d%s%n", route.getRouteNo(), route.getRouteHeading());
        }
        System.out.println();
    }

    public static void printTrips(List<Trip> trips) {
        if (trips.size() == 0) return;

        System.out.println("Route     Next     Status       Route Heading");
        System.out.println("        (in mins)");
        System.out.println("-------------------------------------------------");

        int lastRouteNo = 0;
        for (Trip trip : trips) {
            if (lastRouteNo != trip.getRouteNo() && lastRouteNo != 0)
                System.out.println();
            boolean withIn10Mins = trip.getAdjustedScheduleTime() < 10;
            System.out.printf((withIn10Mins ? ANSI_RED : "") +
                            "%-10d%-9d%-13s%s%n" +
                            (withIn10Mins ? ANSI_RESET : ""),
                    trip.getRouteNo(),
                    trip.getAdjustedScheduleTime(),
                    trip.getGps()[0] == 0.0 ? "Schedule" : "Real-time",
                    trip.getRouteHeading());
            lastRouteNo = trip.getRouteNo();
        }
        System.out.println();
    }

}