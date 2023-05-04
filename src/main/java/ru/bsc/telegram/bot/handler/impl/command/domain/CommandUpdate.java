package ru.bsc.telegram.bot.handler.impl.command.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bsc.telegram.bot.repository.domain.Command;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CommandUpdate {

	private final Command command;
	private final Message message;
	private final Map<String, Object> args;

}
