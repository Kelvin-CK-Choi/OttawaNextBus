package kinchoi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int currentStop = 3047;
        final String getNextTripsForStop = "3";
        final String getNextTripsForStopAllRoutes = "4";
        final String getRouteSummaryForStop = "5";
        final String printStopList = "2";
        final String addStopRouteToFavourite = "6";
        final String removeStopRouteFromFavourite = "7";
        final String getNextTripsForAllFavouriteStopsRoutes = "8";
        final String changeCurrentStop = "1";
        final String exit = "q";

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        Scanner scanner = new Scanner(System.in);
        FavouriteList favouriteList = new FavouriteList();

        System.out.println("Ottawa Next Bus v0.1 developed by Chi Kin Choi");
        System.out.println();

        while (true) {
            System.out.println("Current bus stop: " + ANSI_RED + currentStop + " - " + BusStopList.getStopName(currentStop) + ANSI_RESET );
            System.out.println("-----------------------------------");

            System.out.println(changeCurrentStop + ": Change current stop");
            System.out.println(printStopList + ": Print stop list");
            System.out.println(getNextTripsForStop + ": Get next trips for current stop");
            System.out.println(getNextTripsForStopAllRoutes + ": Get next trips of all routes for current stop");
            System.out.println(getRouteSummaryForStop + ": Get route summary for current stop");
            System.out.println(addStopRouteToFavourite + ": Add stop and route to favourite list");
            System.out.println(removeStopRouteFromFavourite + ": Remove stop and route from favourite list");
            System.out.println(getNextTripsForAllFavouriteStopsRoutes + ": Get next trips for all favourite stops and routes");
            System.out.println(exit + ": Exit app");
            System.out.println();
            System.out.print("Enter an option: ");

            String choice = scanner.nextLine().toLowerCase();
            System.out.println();
            switch (choice) {
                case getNextTripsForStop: {
                    int route = inputAndCheckRouteNo(currentStop);
                    System.out.println();
                    OCNextTripsForStopFeed nextTrip = new OCNextTripsForStopFeed(currentStop, route);
                    PrintOC.printTrips(nextTrip.fetchNextTrips());
                }
                break;

                case getNextTripsForStopAllRoutes: {
                    OCNextTripsForStopAllRoutesFeed nextTripsForStopAllRoutes = new OCNextTripsForStopAllRoutesFeed(currentStop);
                    PrintOC.printTrips(nextTripsForStopAllRoutes.fetchNextTripsAllRoutes());
                }
                break;

                case getRouteSummaryForStop: {
                    OCRouteSummaryForStopFeed routeSummaryForStop = new OCRouteSummaryForStopFeed(3047);
                    PrintOC.printRoutes(routeSummaryForStop.fetchRoutes());
                }
                break;


                case printStopList:
                    BusStopList.printStopList();
                    System.out.println();
                    break;

                case addStopRouteToFavourite: {
                    int routeNo = inputAndCheckRouteNo(currentStop);
                    System.out.println();
                    favouriteList.addStopRouteToFavourite(currentStop, routeNo);
                }
                break;

                case removeStopRouteFromFavourite:
                    favouriteList.removeStopRouteFromFavourite();
                    break;


                case getNextTripsForAllFavouriteStopsRoutes:
                    favouriteList.getNextTripsForAllFavouriteStopsRoutes();
                    break;

                case changeCurrentStop: {
                    currentStop = BusStopList.inputAndCheckStopNo();
                    System.out.println("Current bus stop is changed to " +
                            currentStop + " - " + BusStopList.getStopName(currentStop));
                    System.out.println();
                }
                break;

                case exit:
                    System.out.println("Good bye!");
                    return;

                default:
                    System.out.println("Invalid option.");
                    System.out.println();
            }
        }
    }

    private static int inputAndCheckRouteNo(int stopNo) {
        Scanner scanner = new Scanner(System.in);
        OCRouteSummaryForStopFeed routeSummaryForStop = new OCRouteSummaryForStopFeed(stopNo);
        PrintOC.printRoutes(routeSummaryForStop.fetchRoutes());
        System.out.print("Please choose route: ");

        try {
            int route = scanner.nextInt();
            scanner.nextLine();
            return route;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("Input invalid.");
        }
        return 0;
    }
}

