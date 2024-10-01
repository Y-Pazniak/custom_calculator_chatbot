package by.custom.utilcalculator.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageHandler extends SendMessage {
    public SendMessageHandler(final String chatID, final String text, final String parseMode) {
        super(chatID, text);
        super.setParseMode(parseMode);
    }
}
