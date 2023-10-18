package by.custom.utilcalculator.service;

import by.custom.utilcalculator.controller.Bot;
import by.custom.utilcalculator.directory.resources.Commands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageRouter {
    private final MessagesManager messagesCreator;
    private final BotFieldsManager botFieldsManager;

    private MessageRouter() {
        messagesCreator = MessagesManager.getInstance();
        botFieldsManager = BotFieldsManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesCheckerHolder.MESSAGES_CHECKER;
    }

    public SendMessage checkMessageBeforeExecutionAndGetResult(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage() && message.hasText()) {
            return getCheckInputMessageAndGetAnswer(message);
        } else {
            return messagesCreator.getSorryMessage(message);
        }
    }

    public SendMessage getCheckInputMessageAndGetAnswer(Message message) {
        String usersMessage = message.getText();
        SendMessage sendMessage;
        switch (usersMessage) {
            case Commands.START -> sendMessage = messagesCreator.getGreetingMessage(message);
            case Commands.EAES, Commands.OTHER_COUNTRIES ->
                    sendMessage = botFieldsManager.setCarOriginFieldAndGetNextMessageForUser(message);
            case Commands.PHYSICAL_PERSON, Commands.JURIDICAL_PERSON ->
                    sendMessage = botFieldsManager.setTypeOfOwnerFieldAndGetNextMessageForUser(message);
            case Commands.LESS_3_YEARS_AGE, Commands.BETWEEN_3_AND_7_YEARS_AGE, Commands.MORE_7_YEARS_AGE ->
                    sendMessage = botFieldsManager.setAgeAutoFieldAndGetTheResultMessageForUser(message);
            case Commands.GASOLINE_TYPE_ENGINE, Commands.ELECTRIC_TYPE_ENGINE ->
                    sendMessage = botFieldsManager.setEngineTypeFieldAndGetNextMessageForUser(message);
            case Commands.VOLUME_LESS_1000_CM, Commands.VOLUME_BETWEEN_1000_2000_CM, Commands.VOLUME_BETWEEN_2000_3000_CM,
                    Commands.VOLUME_BETWEEN_3000_3500_CM, Commands.VOLUME_MORE_3500_CM ->
                    sendMessage = botFieldsManager.setEngineVolumeFieldAndGetNextMessageForUser(message);
            default -> sendMessage = messagesCreator.getSorryMessage(message);
        }
        return sendMessage;
    }

    private static class MessagesCheckerHolder {
        private static final MessageRouter MESSAGES_CHECKER = new MessageRouter();
    }
}
