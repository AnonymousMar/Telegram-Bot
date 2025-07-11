package javacor.feature.featureCurrency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javacor.feature.featureCurrency.dto.Currency;
import javacor.feature.featureCurrency.dto.CurrencyItem;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PrivatBankCurrencyService implements  CurrencyService{
    @Override
    public double getRate(Currency currency) throws IOException {
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        //get json
        String json = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get().body()
                .text();
//Convert json => java Object

        Type typeToken =
                TypeToken
                        .getParameterized(List.class, CurrencyItem.class)
                        .getType();

        List <CurrencyItem> currencyItems =  new Gson().fromJson(json, typeToken);


        //Find UAH/USD
        Float uahUsd = currencyItems.stream()
                .filter(it -> it.getCcy() == currency)
                .filter(it -> it.getBase_ccy() == Currency.UAH)
                .map(CurrencyItem::getBuy)
                .findFirst()
                .orElseThrow();

       return uahUsd;
    }
    }

