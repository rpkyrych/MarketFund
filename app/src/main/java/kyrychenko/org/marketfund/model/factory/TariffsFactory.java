package kyrychenko.org.marketfund.model.factory;

import kyrychenko.org.marketfund.model.SocialNetsAndSensor;
import kyrychenko.org.marketfund.model.TalksAndFavouriteCountries;
import kyrychenko.org.marketfund.model.VideoAndMaximumUnlims;
import kyrychenko.org.marketfund.model.basic.AbstractTariff;
import kyrychenko.org.marketfund.model.profit.TariffsProfit;

import static kyrychenko.org.marketfund.model.profit.TariffsProfit.SOCIAL_NETS_AND_SENSOR;
import static kyrychenko.org.marketfund.model.profit.TariffsProfit.TALKS_AND_FAVOURITE_COUNTRIES;
import static kyrychenko.org.marketfund.model.profit.TariffsProfit.UNLIM_VIDEO_AND_MAXIMUM_UNLIM;

public class TariffsFactory {

    public AbstractTariff getTariff(TariffsProfit tariffTitle) throws TypeNotPresentException {
        if (tariffTitle == null) {
            throw new TypeNotPresentException(tariffTitle.name(), new Throwable("tariff with such name not found!"));
        }
        if (tariffTitle.equals(TALKS_AND_FAVOURITE_COUNTRIES)) {
            return new TalksAndFavouriteCountries(TALKS_AND_FAVOURITE_COUNTRIES);
        } else if (tariffTitle.equals(SOCIAL_NETS_AND_SENSOR)) {
            return new SocialNetsAndSensor(SOCIAL_NETS_AND_SENSOR);
        } else if (tariffTitle.equals(UNLIM_VIDEO_AND_MAXIMUM_UNLIM)) {
            return new VideoAndMaximumUnlims(UNLIM_VIDEO_AND_MAXIMUM_UNLIM);
        }
        return null;
    }

}
