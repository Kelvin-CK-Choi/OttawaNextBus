package kinchoi;

import java.util.Arrays;

public class Trip extends Route {

    private int adjustedScheduleTime;
    private double[] gps;
    public Trip(int routeNo, String routeHeading, int adjustedScheduleTime, double[] gps) {
        super(routeNo, routeHeading);
        this.adjustedScheduleTime = adjustedScheduleTime;
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Trip Destination: " + getRouteHeading() + " - " +
                "Next Trip: " + adjustedScheduleTime + " minutes" + " - " +
                "GPS: " + gps[0] + ", " + gps[1];
    }

    public int getAdjustedScheduleTime() {
        return adjustedScheduleTime;
    }

    public double[] getGps() {
        return Arrays.copyOf(gps, gps.length);
    }
}