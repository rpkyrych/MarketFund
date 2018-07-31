package kyrychenko.org.marketfund.view;

import java.util.ArrayList;
import java.util.List;

import kyrychenko.org.marketfund.model.basic.AbstractTariff;

public class PlannedIndicator {
    private static final int PERCENT_OF_THRESHOLD_55_PLUS = 75;
    private int monthPlan;
    private List<AbstractTariff> soldTariffsLess55PlusPayment;
    private List<AbstractTariff> soldTariffsMore55PlusPayment;

    public int getPERCENT_OF_THRESHOLD_55_PLUS() {
        return PERCENT_OF_THRESHOLD_55_PLUS;
    }

    public int getMonthPlan() {
        return monthPlan;
    }

    public void setMonthPlan(int monthPlan) {
        this.monthPlan = monthPlan;
    }

    public List<AbstractTariff> getSoldTariffsLess55PlusPayment() {
        return soldTariffsLess55PlusPayment;
    }

    public void addSoldTariffLess55Payment(List<AbstractTariff> soldTariffsWithoutStartedPayment) {
        if (this.soldTariffsLess55PlusPayment == null) {
            this.soldTariffsLess55PlusPayment = new ArrayList<kyrychenko.org.marketfund.model.basic.AbstractTariff>();
        }
        this.soldTariffsLess55PlusPayment.addAll(soldTariffsWithoutStartedPayment);
    }

    public List<AbstractTariff> getSoldTariffsMore55PlusPayment() {
        return soldTariffsMore55PlusPayment;
    }

    public void addSoldTariffsWith55PlusPayment(List<AbstractTariff> soldTariffsWith55PlusPayment) {
        if (this.soldTariffsMore55PlusPayment == null) {
            this.soldTariffsMore55PlusPayment = new ArrayList<AbstractTariff>();
        }
        this.soldTariffsMore55PlusPayment.addAll(soldTariffsWith55PlusPayment);
    }
}
