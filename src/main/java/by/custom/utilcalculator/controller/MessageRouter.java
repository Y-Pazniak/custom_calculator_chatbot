package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.exception.UtilsborException;
import by.custom.utilcalculator.service.BundleResourcesServant;
import by.custom.utilcalculator.service.UserProgressManager;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

public class MessageRouter {
    private final MessagesCreator messagesCreator;
    private final UserProgressManager userProgressManager;

    private MessageRouter() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressManager = UserProgressManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesRouterHolder.MESSAGES_ROUTER;
    }

    public SendMessage route(final Update update) {
        final Message message = update.getMessage();
        if (!message.hasText()) {
            final SendMessage sorrySendMessage = new SendMessage();
            sorrySendMessage.setChatId(update.getMessage().getChatId().toString());
            sorrySendMessage.setText(getSorryMessage());
            return sorrySendMessage;
        }
        return getCheckInputMessageAndGetAnswer(message);
    }

    //we receive message from user, check it and handle it if it is ok here
    private SendMessage getCheckInputMessageAndGetAnswer(final Message message) {
        final String usersMessage = message.getText();
        final String chatID = message.getChatId().toString();
        final String answer;
        try {
            answer = route(usersMessage, chatID);
        } catch (final UtilsborException e) {
            e.printStackTrace();
            return new SendMessage(chatID, getExceptionText(e));
        }
        return new SendMessage(chatID, answer);
    }

    private String route(final String usersMessage, final String chatID) throws UtilsborException {
        final String answer;
        Command requestingCommand = Command.getCommandByKey(usersMessage);
        switch (requestingCommand) {
            case START -> {
                userProgressManager.createNewUserProgress(chatID);
                answer = getGreetingMessage();
            }

            case EAES, OTHER_COUNTRIES -> {
                answer = userProgressManager.processCarOrigin(requestingCommand, chatID);
            }

            case PHYSICAL_PERSON, JURIDICAL_PERSON -> {
                answer = userProgressManager.processOwnerType(requestingCommand, chatID);
            }

            case LESS_3_YEARS_AGE, MORE_THAN_3_YEARS_AGE -> {
                answer = userProgressManager.processCarAge(requestingCommand, chatID);
            }

            case GASOLINE_TYPE_ENGINE, ELECTRIC_TYPE_ENGINE -> {
                answer = userProgressManager.processEngineType(requestingCommand, chatID);
            }

            case VOLUME_LESS_1000_CM, VOLUME_BETWEEN_1000_2000_CM, VOLUME_BETWEEN_2000_3000_CM, VOLUME_BETWEEN_3000_3500_CM, VOLUME_MORE_3500_CM -> {
                answer = userProgressManager.processEngineVolume(requestingCommand, chatID);

            }

            default -> answer = getSorryMessage();
        }
//        while (true) {
//            if (Objects.equals(usersMessage, Command.START.getCommand())) {
//                userProgressManager.createNewUserProgress(chatID);
//                answer = getGreetingMessage();
//                break;
//            }
//
//            if (Objects.equals(usersMessage, Command.EAES.getCommand()) || Objects.equals(usersMessage, Command.OTHER_COUNTRIES.getCommand())) {
//                answer = userProgressManager.processCarOrigin(usersMessage, chatID);
//                break;
//            }
//
//            if (Objects.equals(usersMessage, Command.PHYSICAL_PERSON.getCommand()) || Objects.equals(usersMessage, Command.JURIDICAL_PERSON.getCommand())) {
//                answer = userProgressManager.processOwnerType(usersMessage, chatID);
//                break;
//            }
//
//            if (Objects.equals(usersMessage, Command.LESS_3_YEARS_AGE.getCommand()) || Objects.equals(usersMessage, Command.MORE_THAN_3_YEARS_AGE.getCommand())) {
//                answer = userProgressManager.processCarAge(usersMessage, chatID);
//                break;
//            }
//
//            if (Objects.equals(usersMessage, Command.GASOLINE_TYPE_ENGINE.getCommand()) || Objects.equals(usersMessage, Command.ELECTRIC_TYPE_ENGINE.getCommand())) {
//                answer = userProgressManager.processEngineType(usersMessage, chatID);
//                break;
//            }
//
//            if (Objects.equals(usersMessage, Command.VOLUME_LESS_1000_CM.getCommand()) ||
//                    Objects.equals(usersMessage, Command.VOLUME_BETWEEN_1000_2000_CM.getCommand()) ||
//                    Objects.equals(usersMessage, Command.VOLUME_BETWEEN_2000_3000_CM.getCommand()) ||
//                    Objects.equals(usersMessage, Command.VOLUME_BETWEEN_3000_3500_CM.getCommand()) ||
//                    Objects.equals(usersMessage, Command.VOLUME_MORE_3500_CM.getCommand())) {
//                answer = userProgressManager.processEngineVolume(usersMessage, chatID);
//                break;
//            }
//            answer = getSorryMessage();
//            break;
//        }

        return answer;
    }

    public String getGreetingMessage() {
        return messagesCreator.getGreeting();
    }

    public String getSorryMessage() {
        return messagesCreator.getSorry();
    }

    private String getExceptionText(final UtilsborException e) {
        return BundleResourcesServant.getInstance().getString("answers." + e.getErrorCode());
    }

    private static class MessagesRouterHolder {
        private static final MessageRouter MESSAGES_ROUTER = new MessageRouter();
    }
}
