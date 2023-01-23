package kinchoi;

import java.util.*;

/**
 * The BusStopList class is a utility class that is used for storing and printing the bus stop information.
 * It has three methods:
 * printStopList(): Prints the stop numbers and names of all the stops in the stopArray.
 * getStopName(int stopNo): Given a stop number, returns the stop name corresponding to that number.
 * inputAndCheckStopNo(): Continuously prompts the user for a stop number until a valid stop number is entered and returns that number.
 * inputStop(): Prompts the user for a stop number and returns the entered number.
 *
 * @author kinchoi
 */
public class BusStopList {

    private static final Stop[] stopArray = new Stop[]{
            new Stop(3039, "Airport"),
            new Stop(3045, "Barrhaven Centre"),
            new Stop(3017, "Baseline"),
            new Stop(3050, "Bayshore"),
            new Stop(3060, "Bayview"),
            new Stop(3049, "Beatrice"),
            new Stop(3034, "Billings Bridge"),
            new Stop(3027, "Blair"),
            new Stop(3059, "Canadian Tire Centre"),
            new Stop(3062, "Carleton"),
            new Stop(3061, "Carling"),
            new Stop(3074, "Chapel Hill"),
            new Stop(3026, "Cyrville"),
            new Stop(3013, "Dominion"),
            new Stop(3055, "Eagleson"),
            new Stop(3043, "Fallowfield - VIA Rail"),
            new Stop(3037, "Greenboro"),
            new Stop(3035, "Heron"),
            new Stop(3023, "Hurdman"),
            new Stop(3057, "Innovation"),
            new Stop(3016, "Iris"),
            new Stop(3070, "Jeanne d'Arc"),
            new Stop(3022, "Lees"),
            new Stop(3041, "Leitrim"),
            new Stop(3014, "Lincoln Fields"),
            new Stop(3046, "Longfields"),
            new Stop(3030, "Lycée Claudel"),
            new Stop(3051, "Lyon"),
            new Stop(3047, "Marketplace"),
            new Stop(3076, "Millennium"),
            new Stop(3042, "Moodie"),
            new Stop(3063, "Mooney's Bay"),
            new Stop(3048, "Nepean Woods"),
            new Stop(3052, "Parliament"),
            new Stop(3010, "Pimisi"),
            new Stop(3019, "Pinecrest"),
            new Stop(3028, "Place d'Orléans"),
            new Stop(3075, "Place d'Orléans Park & Ride"),
            new Stop(3033, "Pleasant Park"),
            new Stop(3015, "Queensway"),
            new Stop(3009, "Rideau"),
            new Stop(3032, "Riverside"),
            new Stop(3040, "Riverview"),
            new Stop(3031, "Smyth"),
            new Stop(3038, "South Keys"),
            new Stop(3025, "St-Laurent"),
            new Stop(3044, "Strandherd"),
            new Stop(3018, "Teron"),
            new Stop(3058, "Terry Fox"),
            new Stop(3024, "Tremblay - VIA Rail"),
            new Stop(3029, "Trim"),
            new Stop(3011, "Tunney's Pasture"),
            new Stop(3021, "uOttawa"),
            new Stop(3036, "Walkley"),
            new Stop(3012, "Westboro")
    };

    public static void printStopList() {
        for (Stop stop : stopArray) {
            System.out.println(stop.getStopNo() + " - " + stop.getStopName());
        }
    }

    public static String getStopName(int stopNo) {
        for (Stop stop : stopArray) {
            if (stopNo == stop.getStopNo())
                return stop.getStopName();
        }
        return "No stop found";
    }

    public static int inputAndCheckStopNo() {
        int stopNo;
        do {
            stopNo = inputStop();
        } while (stopNo == 0);
        return stopNo;
    }

    public static int inputStop() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter stop number or name: ");

        if (scanner.hasNextInt()) {
            int userInput = scanner.nextInt();
            for (Stop stop : stopArray) {
                if (stop.getStopNo() == userInput) return userInput;
            }
            System.out.println("No stop matched. Please check again.");
            System.out.println();
            return 0;
        } else {
            String userInput = scanner.nextLine();
            ArrayList<Stop> matchedStopList = new ArrayList<>();
            for (Stop stop : stopArray) {
                if (stop.getStopName().toLowerCase().contains(userInput.toLowerCase()))
                    matchedStopList.add(stop);
            }
            if (matchedStopList.size() == 0) {
                System.out.println("No stop matched. Please check again.");
                System.out.println();
            } else if (matchedStopList.size() == 1)
                return matchedStopList.get(0).getStopNo();
            else {
                while (true) {
                    for (int i = 0; i < matchedStopList.size(); i++) {
                        System.out.println(i + 1 + ": " + matchedStopList.get(i));
                    }
                    System.out.println();
                    System.out.print("Please choose (1 to " + matchedStopList.size() + "): ");
                    if (scanner.hasNextInt()) {
                        int userChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (userChoice <= matchedStopList.size())
                            return matchedStopList.get(userChoice - 1).getStopNo();
                    } else scanner.nextLine();
                    System.out.println("Input invalid.");
                }
            }
        }
        return 0;
    }

    private static class Stop {
        private int stopNo;
        private String stopName;

        public Stop(int stopNo, String stopName) {
            this.stopNo = stopNo;
            this.stopName = stopName;
        }

        public int getStopNo() {
            return stopNo;
        }

        public String getStopName() {
            return stopName;
        }

        @Override
        public String toString() {
            return stopNo + " - " + stopName;
        }
    }
}


