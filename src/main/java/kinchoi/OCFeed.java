package kinchoi;

public abstract class OCFeed {

    static final String appID = "5eb15251";
    static final String apiKey = "feb28d776f1df9d7147c447e9ee7f336";
    private int stopNo;
    private int routeNo;


    public OCFeed(int stopNo) {
        this.stopNo = stopNo;
    }

    public OCFeed(int stopNo, int routeNo) {
        this.stopNo = stopNo;
        this.routeNo = routeNo;
    }

    public int getStopNo() {
        return stopNo;
    }

    public int getRouteNo() {
        return routeNo;
    }



}
