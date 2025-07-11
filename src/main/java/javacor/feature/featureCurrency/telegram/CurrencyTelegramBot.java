package javacor.feature.featureCurrency.telegram;

import javacor.feature.featureCurrency.CurrencyService;
import javacor.feature.featureCurrency.PrivatBankCurrencyService;
import javacor.feature.featureCurrency.dto.Currency;
import javacor.feature.featureCurrency.featureUI.PrettyPrintCurrencyService;
import javacor.feature.featureCurrency.telegram.command.HelpCommand;
import javacor.feature.featureCurrency.telegram.command.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot{
    private CurrencyService currencyService;
    private PrettyPrintCurrencyService prettyPrintCurrencyService;
    public CurrencyTelegramBot(){
        currencyService = new PrivatBankCurrencyService();
        prettyPrintCurrencyService = new PrettyPrintCurrencyService();
        register(new StartCommand());
        register(new HelpCommand());
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            Currency currency = Currency.valueOf(callbackQuery);

            double currencyRate = 0;
            try {
                currencyRate = currencyService.getRate(currency);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String prettyText = prettyPrintCurrencyService.convert(currencyRate, currency);

            SendMessage responseMessage = new SendMessage();
            responseMessage.setText(prettyText);

          long chatId = update.getCallbackQuery().getMessage().getChatId();
          responseMessage.setChatId(Long.toString(chatId));
            try {
                execute(responseMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasMessage()){
            String message = update.getMessage().getText();
            String responseText = "You wrote:" + message;SendMessage sendMessage = new SendMessage();
            sendMessage.setText(responseText); sendMessage.setChatId(Long.toString(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

}
}