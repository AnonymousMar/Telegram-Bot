package javacor.feature.featureCurrency.telegram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {
    public HelpCommand() {
        super("help", "Help command ");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        SendMessage helpText =  new SendMessage();
        helpText.setText("A bot that helps you check the exchange rate");
        helpText.setChatId(Long.toString(chat.getId()));
        try {
            absSender.execute(helpText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
