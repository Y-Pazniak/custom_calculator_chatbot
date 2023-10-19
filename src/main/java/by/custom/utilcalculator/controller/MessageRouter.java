package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.directory.resources.Command;
import by.custom.utilcalculator.service.BotFieldsManager;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageRouter {
    private final MessagesCreator messagesCreator;
    private final BotFieldsManager botFieldsManager;

    private MessageRouter() {
        messagesCreator = MessagesCreator.getInstance();
        botFieldsManager = BotFieldsManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesCheckerHolder.MESSAGES_CHECKER;
    }

    public SendMessage checkMessageBeforeExecutionAndGetResult(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            return getCheckInputMessageAndGetAnswer(message);
        } else {
            SendMessage sorrySendMessage = new SendMessage();
            sorrySendMessage.setChatId(update.getMessage().getChatId().toString());
            sorrySendMessage.setText(getSorryMessage());
            return sorrySendMessage;
        }
    }

    //we receive message from user, check it and handle it if it is ok here
    public SendMessage getCheckInputMessageAndGetAnswer(Message message) {
        String usersMessage = message.getText();
        String userID = message.getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userID);

        switch (usersMessage) {
            case Command.START -> sendMessage.setText(getGreetingMessage());
            case Command.EAES, Command.OTHER_COUNTRIES ->
                    sendMessage.setText(botFieldsManager.processCarOrigin(usersMessage));
            case Command.PHYSICAL_PERSON, Command.JURIDICAL_PERSON ->
                    sendMessage.setText(botFieldsManager.processOwnerType(usersMessage));
            case Command.LESS_3_YEARS_AGE, Command.BETWEEN_3_AND_7_YEARS_AGE, Command.MORE_7_YEARS_AGE ->
                    sendMessage.setText(botFieldsManager.processCarAge(usersMessage));
            case Command.GASOLINE_TYPE_ENGINE, Command.ELECTRIC_TYPE_ENGINE ->
                    sendMessage.setText(botFieldsManager.processEngineType(usersMessage));
            case Command.VOLUME_LESS_1000_CM, Command.VOLUME_BETWEEN_1000_2000_CM, Command.VOLUME_BETWEEN_2000_3000_CM,
                    Command.VOLUME_BETWEEN_3000_3500_CM, Command.VOLUME_MORE_3500_CM ->
                    sendMessage.setText(botFieldsManager.processEngineVolume(usersMessage));
            default -> sendMessage.setText(getSorryMessage());
        }
        return sendMessage;
    }

    public String createSendMessage() {
        return messagesCreator.getCountryOrigin();
    }

    public String getGreetingMessage() {
        return messagesCreator.getGreeting();
    }

    public String getSorryMessage() {
        return messagesCreator.getSorry();
    }

    private static class MessagesCheckerHolder {
        private static final MessageRouter MESSAGES_CHECKER = new MessageRouter();
    }
}
