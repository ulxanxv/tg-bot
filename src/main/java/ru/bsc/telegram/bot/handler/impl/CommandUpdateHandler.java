package ru.bsc.telegram.bot.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bsc.telegram.bot.handler.UpdateHandler;
import ru.bsc.telegram.bot.handler.impl.command.CommandHandler;
import ru.bsc.telegram.bot.handler.impl.command.domain.CommandUpdate;
import ru.bsc.telegram.bot.listener.domain.TelegramUpdate;
import ru.bsc.telegram.bot.repository.CommandRepository;
import ru.bsc.telegram.bot.repository.domain.Command;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandUpdateHandler implements UpdateHandler {

	private static final Pattern COMMAND_PATTERN = Pattern.compile("/[A-Za-z0-9]*");
	private static final Pattern ARGS_PATTERN = Pattern.compile("\\s*(-)([A-Za-z0-9]*)=?([A-Za-z0-9=]*)?\\s?");

	private final CommandRepository commandRepository;
	private final List<CommandHandler> commandHandlers;
	private final Map<Command, CommandHandler> commandHandlersAsMap = new ConcurrentHashMap<>();

	@Override
	public void handle(TelegramUpdate update) throws TelegramApiException {
		handle(update.getSender(), update.getEvent());
	}

	private void handle(AbsSender sender, Update update) throws TelegramApiException {
		final String commandPath = extractCommandPath(update.getMessage().getText());
		final Map<String, Object> commandArgs = extractCommandArgs(update.getMessage().getText());
		log.debug("Command is {} and args is {}", commandPath, StringUtils.join(commandArgs));

		final Command command = findCommand(commandPath);
		final CommandHandler commandHandler = commandHandlersAsMap.computeIfAbsent(command, this::computeCommandHandler);
		log.info("{} was recognized as {}", update.getUpdateId(), commandHandler.getClass().getSimpleName());
		commandHandler.handle(sender, new CommandUpdate(command, update.getMessage(), commandArgs));
	}

	private String extractCommandPath(String text) {
		final Matcher matcher = COMMAND_PATTERN.matcher(text);
		return matcher.find()
				? matcher.group(0)
				: null;
	}

	private Map<String, Object> extractCommandArgs(String text) {
		return ARGS_PATTERN.matcher(text)
				.results()
				.collect(toMap(
						mr -> mr.group(2),
						mr -> isEmpty(mr.group(3))
								? true
								: mr.group(3),
						(v1, v2) -> v2
				));
	}

	private Command findCommand(String commandPath) {
		return commandRepository.findCommandByPath(commandPath).orElse(commandRepository.findDefaultCommand());
	}

	private CommandHandler computeCommandHandler(Command command) {
		return commandHandlers.stream()
				.filter(ch -> Objects.equals(command.getCode(), ch.getSupportedType()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown command (default action also absent)"));
	}

	@Override
	public boolean supports(TelegramUpdate update) {
		final Message message = update.getEvent().getMessage();
		return nonNull(message) && message.hasText() && message.getText().startsWith("/");
	}
}
