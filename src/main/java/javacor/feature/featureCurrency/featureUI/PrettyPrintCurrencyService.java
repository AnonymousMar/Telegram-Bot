package javacor.feature.featureCurrency.featureUI;

import javacor.feature.featureCurrency.dto.Currency;

public class PrettyPrintCurrencyService {
    public String convert(double rate, Currency currency){
        String templete = "Exchange rate ${currency} => UAH = ${rate}";
        float roundRate = Math.round(rate * 100d) / 100.f;
        return templete
                .replace("${currency}", currency.name())
                .replace("${rate}", roundRate + "");
    }
}
