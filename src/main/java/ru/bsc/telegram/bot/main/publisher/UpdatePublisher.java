package ru.bsc.telegram.bot.main.publisher;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bsc.telegram.bot.listener.TelegramUpdateListener;

public interface UpdatePublisher {

	/**
	 * Публикует сообщения для обработки в {@link TelegramUpdateListener}.
	 */
	void publishUpdate(AbsSender sender, Update update);

}
