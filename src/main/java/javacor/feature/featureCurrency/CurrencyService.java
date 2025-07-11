package javacor.feature.featureCurrency;

import javacor.feature.featureCurrency.dto.Currency;

import java.io.IOException;

public interface CurrencyService {
    double getRate(Currency currency) throws IOException;
}
