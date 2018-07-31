package kyrychenko.org.marketfund.model.basic;

import kyrychenko.org.marketfund.model.profit.TariffsProfit;

public abstract class AbstractTariff {
    private int belowPlan;
    private int fulfilledPlan;
    private int abovePlan;

    public AbstractTariff(TariffsProfit abstractTariff) {
        this.belowPlan = abstractTariff.getBelowPlan();
        this.fulfilledPlan = abstractTariff.getFulfilledPlan();
        this.abovePlan = abstractTariff.getAbovePlan();
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
