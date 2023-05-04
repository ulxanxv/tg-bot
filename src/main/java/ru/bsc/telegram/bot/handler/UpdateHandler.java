package ru.bsc.telegram.bot.handler;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bsc.telegram.bot.listener.domain.TelegramUpdate;

public interface UpdateHandler {

	void handle(TelegramUpdate update) throws TelegramApiException;

	boolean supports(TelegramUpdate update);

}
