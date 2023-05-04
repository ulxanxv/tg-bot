package ru.bsc.telegram.bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.bsc.telegram.bot.handler.impl.command.CommandHandler;
import ru.bsc.telegram.bot.repository.domain.Command;

import java.util.Optional;

@Repository
public interface CommandRepository extends CrudRepository<Command, String> {

	Optional<Command> findCommandByPath(String path);

	@NonNull
	@Query("SELECT c FROM Command c WHERE CODE = ru.bsc.telegram.bot.repository.domain.Command.DEFAULT_CODE")
	Command findDefaultCommand();

}
