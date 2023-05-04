package ru.bsc.telegram.bot.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TelegramBotInitializer {

	private final TelegramBot bot;
	private final TelegramBotsApi telegramBotsApi;

	@PostConstruct
	public void init() throws TelegramApiException {
		telegramBotsApi.registerBot(bot);
	}
}
