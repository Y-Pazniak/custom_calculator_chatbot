package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.exception.UserFileNotFoundException;
import by.custom.utilcalculator.service.CustomSendMessage;
import by.custom.utilcalculator.service.UserProgressManager;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageRouter {
    private final MessagesCreator messagesCreator;
    private final UserProgressManager userProgressManager;

    private MessageRouter() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressManager = UserProgressManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesCheckerHolder.MESSAGES_CHECKER;
    }

    public SendMessage route(final Update update) {
        Message message = update.getMessage();
        if (!message.hasText()) {
            SendMessage sorrySendMessage = new SendMessage();
            sorrySendMessage.setChatId(update.getMessage().getChatId().toString());
            sorrySendMessage.setText(getSorryMessage());
            return sorrySendMessage;
        }
        return getCheckInputMessageAndGetAnswer(message);
    }

    //we receive message from user, check it and handle it if it is ok here
    private SendMessage getCheckInputMessageAndGetAnswer(final Message message) {
        String usersMessage = message.getText();
        String chatID = message.getChatId().toString();
        String answer;
        try {
            switch (usersMessage) {
                case Command.START -> {
                    userProgressManager.createNewUserProgress(chatID);
                    answer = getGreetingMessage();
                }
                case Command.EAES, Command.OTHER_COUNTRIES ->
                        answer = userProgressManager.processCarOrigin(usersMessage, chatID);
                case Command.PHYSICAL_PERSON, Command.JURIDICAL_PERSON ->
                        answer = userProgressManager.processOwnerType(usersMessage, chatID);
                case Command.LESS_3_YEARS_AGE, Command.BETWEEN_3_AND_7_YEARS_AGE, Command.MORE_7_YEARS_AGE ->
                        answer = userProgressManager.processCarAge(usersMessage, chatID);
                case Command.GASOLINE_TYPE_ENGINE, Command.ELECTRIC_TYPE_ENGINE ->
                        answer = userProgressManager.processEngineType(usersMessage, chatID);
                case Command.VOLUME_LESS_1000_CM, Command.VOLUME_BETWEEN_1000_2000_CM, Command.VOLUME_BETWEEN_2000_3000_CM,
                        Command.VOLUME_BETWEEN_3000_3500_CM, Command.VOLUME_MORE_3500_CM ->
                        answer = userProgressManager.processEngineVolume(usersMessage, chatID);
                default -> answer = getSorryMessage();
            }
        } catch (UserFileNotFoundException e) {
            return new CustomSendMessage(chatID, e.getMessage());
        }
        return new CustomSendMessage(chatID, answer);
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
