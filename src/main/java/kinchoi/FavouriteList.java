package kinchoi;

import java.util.ArrayList;
import java.util.Scanner;

public class FavouriteList {
    private ArrayList<StopRoute> favouriteList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStopRouteToFavourite(int stopNo, int routeNo) {
        boolean duplicate = false;
        for (StopRoute stopRoute : favouriteList) {
            if (stopRoute.getStopNo() == stopNo && stopRoute.getRouteNo() == routeNo) {
                System.out.println("Stop no " + stopNo + " - route no " + routeNo + " is already in the favourite list");
                System.out.println();
                duplicate = true;
            }
        }
        if (duplicate == false) {
            favouriteList.add(new StopRoute(stopNo, routeNo));
            System.out.println("Stop no " + stopNo + " - route no " + routeNo + " is added to favourite list.");
            System.out.println();
        }
    }

    public void removeStopRouteFromFavourite() {
        if (favouriteList.size() == 0) {
            System.out.println("There is no record in the favourite list.\n");
            return;
        } ;

        for (int i = 0; i < favouriteList.size(); i++) {
            System.out.println(i + 1 + ": " +
                    favouriteList.get(i).getStopNo() + " " + BusStopList.getStopName(favouriteList.get(i).getStopNo()) + " - " +
                    favouriteList.get(i).getRouteNo());
        }
        System.out.print("Please choose which route to delete: (1 to " + favouriteList.size() + "): ");
        if (scanner.hasNextInt()) {
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            if (userChoice <= favouriteList.size()) {
                System.out.println(
                        favouriteList.get(userChoice - 1).getStopNo() + " " + BusStopList.getStopName(favouriteList.get(userChoice - 1).getStopNo()) + " - " +
                                favouriteList.get(userChoice - 1).getRouteNo() + " is removed.");
                System.out.println();
                favouriteList.remove(userChoice - 1);
            } else System.out.println("Input invalid.");
        } else {
            scanner.nextLine();
            System.out.println("Input invalid.");
        }
    }

    public void getNextTripsForAllFavouriteStopsRoutes() {
        if (favouriteList.size() == 0) System.out.println("There is no record in the favourite list.\n");
        else
            for (StopRoute stopRoute : favouriteList) {
                OCNextTripsForStopFeed nextTrip = new OCNextTripsForStopFeed(stopRoute.getStopNo(), stopRoute.getRouteNo());
                System.out.println("Bus stop: " + stopRoute.getStopNo() + " - " + BusStopList.getStopName(stopRoute.getStopNo()));
                System.out.println("Route no: " + stopRoute.getRouteNo());
                PrintOC.printTrips(nextTrip.fetchNextTrips());
            }
    }
}
