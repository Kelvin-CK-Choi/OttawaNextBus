package kinchoi;

public class Route {
    private int routeNo;
    private String routeHeading;

    public Route(int routeNo, String routeHeading) {
        this.routeNo = routeNo;
        this.routeHeading = routeHeading;
    }

    @Override
    public String toString() {
        return "Route No: " + routeNo + " - " +
                "Route Heading: " + routeHeading;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public String getRouteHeading() {
        return routeHeading;
    }
}