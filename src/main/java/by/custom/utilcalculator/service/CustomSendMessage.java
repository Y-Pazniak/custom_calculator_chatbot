package by.custom.utilcalculator.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CustomSendMessage extends SendMessage {

    public CustomSendMessage(final String chatID, final String answer) {
        super.setChatId(chatID);
        super.setText(answer);
    }
}
