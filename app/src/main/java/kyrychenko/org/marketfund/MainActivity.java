package kyrychenko.org.marketfund;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import kyrychenko.org.marketfund.controller.CalculateController;

public class MainActivity extends AppCompatActivity {
    private CalculateController calculateController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculateController = new CalculateController();
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {

        EditText monthPlanEditText = findViewById(R.id.monthPlanInput);
        int value = getValueOfEditTextIfNotNull(monthPlanEditText);
        if (value == 0) return;
        calculateController.setMonthPlan(value);

        EditText talksAndCountriesLess55EditText = findViewById(R.id.talksAndCountriesLess55Input);
        calculateController.setTalksAndCountriesLess55Input(getValueOfEditTextIfNotNull(talksAndCountriesLess55EditText));

        EditText talksAndCountriesMore55EditText = findViewById(R.id.talksAndCountriesMore55Input);
        calculateController.setTalksAndCountriesMore55Input(getValueOfEditTextIfNotNull(talksAndCountriesMore55EditText));

        EditText socialNetsAndSensorLess55EditText = findViewById(R.id.socialNetsAndSensorLess55Input);
        calculateController.setSocialNetsAndSensorLess55Input(getValueOfEditTextIfNotNull(socialNetsAndSensorLess55EditText));

        EditText socialNetsAndSensorMore55EditText = findViewById(R.id.socialNetsAndSensorMore55Input);
        calculateController.setSocialNetsAndSensorMore55Input(getValueOfEditTextIfNotNull(socialNetsAndSensorMore55EditText));

        EditText videoAndMaximumLess55EditText = findViewById(R.id.videoAndMaximumLess55Input);
        calculateController.setVideoAndMaximumLess55Input(getValueOfEditTextIfNotNull(videoAndMaximumLess55EditText));

        EditText videoAndMaximumMore55EditText = findViewById(R.id.videoAndMaximumMore55Input);
        calculateController.setVideoAndMaximumMore55Input(getValueOfEditTextIfNotNull(videoAndMaximumMore55EditText));

        printProfit();
        calculateController.reset();
    }


    public void reset(View view) {
        calculateController.reset();
        setContentView(R.layout.activity_main);
    }

    private void printProfit() {
        String pieces = " шт.";
        String percent = " %";
        String profit = " грн";

        int quantityOfSoldTariffs = calculateController.getQuantityOfSoldTariffs();
        findViewByIdAndSetTextIsNotEmpty(R.id.actualRateInput, quantityOfSoldTariffs, pieces);

        double totalPercentOfActualRate = calculateController.getTotalPercentOfActualRate();
        findViewByIdAndSetTextIsNotEmpty(R.id.totalPercentRate, (int) totalPercentOfActualRate, percent);

        double percentOfThreshold55Plus = calculateController.getPercentOfThreshold55Plus();
        findViewByIdAndSetTextIsNotEmpty(R.id.soldTariffs55Plus, (int) percentOfThreshold55Plus, percent);

        int soldTariffsLess55Plus = calculateController.getSoldTariffsLess55Plus();
        findViewByIdAndSetTextIsNotEmpty(R.id.totalSoldLess55Tariffs, soldTariffsLess55Plus, pieces);

        int soldTariffsMore55Plus = calculateController.getSoldTariffsMore55Plus();
        findViewByIdAndSetTextIsNotEmpty(R.id.totalSoldMore55Tariffs, soldTariffsMore55Plus, pieces);

        int talksAndFavouriteCountriesProfit = calculateController.getTalksAndFavouriteCountriesProfit();
        findViewByIdAndSetTextIsNotEmpty(R.id.talksAndCountriesProfit, talksAndFavouriteCountriesProfit, profit);

        int socialNetsAndSensorProfit = calculateController.getSocialNetsAndSensorProfit();
        findViewByIdAndSetTextIsNotEmpty(R.id.socialNetsAndSensorProfit, socialNetsAndSensorProfit, profit);

        int videoAndMaximumUnlimsProfit = calculateController.getVideoAndMaximumUnlimsProfit();
        findViewByIdAndSetTextIsNotEmpty(R.id.videoAndMaximumProfit, videoAndMaximumUnlimsProfit, profit);

        int totalProfit = talksAndFavouriteCountriesProfit + socialNetsAndSensorProfit + videoAndMaximumUnlimsProfit;
        findViewByIdAndSetTextIsNotEmpty(R.id.totalProfit, totalProfit, profit);
    }

    private int getValueOfEditTextIfNotNull(EditText editText) {
        if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
            return Integer.parseInt(editText.getText().toString());
        }
        return 0;
    }

    private void findViewByIdAndSetTextIsNotEmpty(int textViewId, int setValue, String suffix) {
        TextView textView = findViewById(textViewId);
        if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
            textView.setText(setValue + suffix);
        }
    }


}
