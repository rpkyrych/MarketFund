package kyrychenko.org.marketfund.model.profit;

public enum TariffsProfit {
    TALKS_AND_FAVOURITE_COUNTRIES(10, 15, 20),
    SOCIAL_NETS_AND_SENSOR(20, 30, 35),
    UNLIM_VIDEO_AND_MAXIMUM_UNLIM(30, 45, 55);

    private int belowPlan;
    private int fulfilledPlan;
    private int abovePlan;

    TariffsProfit(int belowPlan, int fulfilledPlan, int abovePlan) {
        this.belowPlan = belowPlan;
        this.fulfilledPlan = fulfilledPlan;
        this.abovePlan = abovePlan;
    }

    public int getBelowPlan() {
        return belowPlan;
    }

    public int getFulfilledPlan() {
        return fulfilledPlan;
    }

    public int getAbovePlan() {
        return abovePlan;
    }
}
