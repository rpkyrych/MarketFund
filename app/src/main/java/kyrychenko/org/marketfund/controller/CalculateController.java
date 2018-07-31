package kyrychenko.org.marketfund.controller;

import java.util.ArrayList;
import java.util.List;

import kyrychenko.org.marketfund.model.SocialNetsAndSensor;
import kyrychenko.org.marketfund.model.TalksAndFavouriteCountries;
import kyrychenko.org.marketfund.model.VideoAndMaximumUnlims;
import kyrychenko.org.marketfund.model.basic.AbstractTariff;
import kyrychenko.org.marketfund.model.factory.TariffsFactory;
import kyrychenko.org.marketfund.model.profit.TariffsProfit;
import kyrychenko.org.marketfund.view.PlannedIndicator;

import static kyrychenko.org.marketfund.model.profit.TariffsProfit.SOCIAL_NETS_AND_SENSOR;
import static kyrychenko.org.marketfund.model.profit.TariffsProfit.TALKS_AND_FAVOURITE_COUNTRIES;
import static kyrychenko.org.marketfund.model.profit.TariffsProfit.UNLIM_VIDEO_AND_MAXIMUM_UNLIM;

public class CalculateController {
    private TariffsFactory tariffsFactory;
    private PlannedIndicator plannedIndicator;

    public CalculateController() {
        plannedIndicator = new PlannedIndicator();
        tariffsFactory = new TariffsFactory();
    }

    public void setMonthPlan(int quantity) {
        plannedIndicator.setMonthPlan(quantity);
    }

    public void setTalksAndCountriesLess55Input(int quantity) {
        plannedIndicator.addSoldTariffLess55Payment(
                createLimitedTariffsListByType(TALKS_AND_FAVOURITE_COUNTRIES, quantity)
        );
    }

    public void setTalksAndCountriesMore55Input(int quantity) {
        plannedIndicator.addSoldTariffsWith55PlusPayment(
                createLimitedTariffsListByType(TALKS_AND_FAVOURITE_COUNTRIES, quantity)
        );
    }

    public void setSocialNetsAndSensorLess55Input(int quantity) {
        plannedIndicator.addSoldTariffLess55Payment(
                createLimitedTariffsListByType(SOCIAL_NETS_AND_SENSOR, quantity)
        );
    }

    public void setSocialNetsAndSensorMore55Input(int quantity) {
        plannedIndicator.addSoldTariffsWith55PlusPayment(
                createLimitedTariffsListByType(SOCIAL_NETS_AND_SENSOR, quantity)
        );
    }

    public void setVideoAndMaximumLess55Input(int quantity) {
        plannedIndicator.addSoldTariffLess55Payment(
                createLimitedTariffsListByType(UNLIM_VIDEO_AND_MAXIMUM_UNLIM, quantity)
        );
    }

    public void setVideoAndMaximumMore55Input(int quantity) {
        plannedIndicator.addSoldTariffsWith55PlusPayment(
                createLimitedTariffsListByType(UNLIM_VIDEO_AND_MAXIMUM_UNLIM, quantity)
        );
    }

    public int getQuantityOfSoldTariffs() {
        return plannedIndicator.getSoldTariffsMore55PlusPayment().size() + plannedIndicator.getSoldTariffsLess55PlusPayment().size();
    }

    public double getTotalPercentOfActualRate() {
        return getQuantityOfSoldTariffs() * 100.0 / plannedIndicator.getMonthPlan();
    }

    public double getPercentOfThreshold55Plus() {
        return plannedIndicator.getSoldTariffsMore55PlusPayment().size() * 100.0 / getQuantityOfSoldTariffs();
    }

    public int getSoldTariffsLess55Plus() {
        return plannedIndicator.getSoldTariffsLess55PlusPayment().size();
    }

    public int getSoldTariffsMore55Plus() {
        return plannedIndicator.getSoldTariffsMore55PlusPayment().size();
    }

    public int getTalksAndFavouriteCountriesProfit() {
        int profit = 0;

        if (is55PlusThresholdAchieved()) {
            List<AbstractTariff> calculatedTariffs = new ArrayList<>();
            for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsLess55PlusPayment()) {
                if (abstractTariff instanceof TalksAndFavouriteCountries) {
                    calculatedTariffs.add(abstractTariff);
                }
            }
            profit += calculateProfitFromTariffsList(calculatedTariffs);
        }

        List<AbstractTariff> calculatedTariffs = new ArrayList<>();
        for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsMore55PlusPayment()) {
            if (abstractTariff instanceof TalksAndFavouriteCountries) {
                calculatedTariffs.add(abstractTariff);
            }
        }
        profit += calculateProfitFromTariffsList(calculatedTariffs);

        return profit;
    }

    public int getSocialNetsAndSensorProfit() {
        int profit = 0;


        if (is55PlusThresholdAchieved()) {
            List<AbstractTariff> calculatedTariffs = new ArrayList<>();
            for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsLess55PlusPayment()) {
                if (abstractTariff instanceof SocialNetsAndSensor) {
                    calculatedTariffs.add(abstractTariff);
                }
            }
            profit += calculateProfitFromTariffsList(calculatedTariffs);
        }

        List<AbstractTariff> calculatedTariffs = new ArrayList<>();
        for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsMore55PlusPayment()) {
            if (abstractTariff instanceof SocialNetsAndSensor) {
                calculatedTariffs.add(abstractTariff);
            }
        }
        profit += calculateProfitFromTariffsList(calculatedTariffs);

        return profit;
    }

    public int getVideoAndMaximumUnlimsProfit() {
        int profit = 0;


        if (is55PlusThresholdAchieved()) {
            List<AbstractTariff> calculatedTariffs = new ArrayList<>();
            for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsLess55PlusPayment()) {
                if (abstractTariff instanceof VideoAndMaximumUnlims) {
                    calculatedTariffs.add(abstractTariff);
                }
            }
            profit += calculateProfitFromTariffsList(calculatedTariffs);
        }

        List<AbstractTariff> calculatedTariffs = new ArrayList<>();
        for (AbstractTariff abstractTariff : plannedIndicator.getSoldTariffsMore55PlusPayment()) {
            if (abstractTariff instanceof VideoAndMaximumUnlims) {
                calculatedTariffs.add(abstractTariff);
            }
        }
        profit += calculateProfitFromTariffsList(calculatedTariffs);

        return profit;
    }

    public void reset() {
        plannedIndicator = new PlannedIndicator();
    }

    private List<AbstractTariff> createLimitedTariffsListByType(TariffsProfit profit, int limit) {
        List<AbstractTariff> tariffs = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            tariffs.add(tariffsFactory.getTariff(profit));
        }
        return tariffs;
    }

    private int calculateProfitFromTariffsList(List<AbstractTariff> tariffList) {
        int result = 0;

        if (getTotalPercentOfActualRate() < 90) {
            for (AbstractTariff tariff : tariffList) {
                result += tariff.getBelowPlan();
            }

        } else if (getTotalPercentOfActualRate() >= 110) {
            for (AbstractTariff tariff : tariffList) {
                result += tariff.getAbovePlan();
            }
        } else {
            for (AbstractTariff tariff : tariffList) {
                result += tariff.getFulfilledPlan();
            }
        }
        tariffList.clear();
        return result;
    }

    private boolean is55PlusThresholdAchieved() {
        return getPercentOfThreshold55Plus() > plannedIndicator.getPERCENT_OF_THRESHOLD_55_PLUS();
    }
}
