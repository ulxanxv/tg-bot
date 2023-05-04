package ru.bsc.telegram.bot.main.registrar;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface UpdateRegistrar {

	/**
	 * Регистрирует пришедшее событие.
	 */
	void registerUpdate(AbsSender sender, Update update);

}
