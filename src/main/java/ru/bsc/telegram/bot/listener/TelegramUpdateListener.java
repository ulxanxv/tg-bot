package ru.bsc.telegram.bot.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bsc.telegram.bot.handler.UpdateHandler;
import ru.bsc.telegram.bot.listener.domain.TelegramUpdate;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramUpdateListener implements ApplicationListener<TelegramUpdate> {

	private final List<UpdateHandler> updateHandlers;

	@Override
	public void onApplicationEvent(TelegramUpdate update) {
		final UpdateHandler handler = getUpdateHandler(update);
		if (isNull(handler)) {
			log.warn("Update handler not found for update {}", update.getEvent().getUpdateId());
			return;
		}

		log.info("Update handler {} resolved for update {}", handler.getClass().getSimpleName(), update.getEvent().getUpdateId());
		try {
			handler.handle(update);
		} catch (TelegramApiException e) {
			log.error("Some error occurred while handle update {}: {}", update.getEvent().getUpdateId(), e.getMessage());
		}
	}

	private UpdateHandler getUpdateHandler(TelegramUpdate update) {
		return updateHandlers.stream()
				.filter(uh -> uh.supports(update))
				.findFirst()
				.orElse(null);
	}
}
