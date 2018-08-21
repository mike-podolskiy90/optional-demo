package com.epam.mix.optionaldemo.case9.objects;

public class CommandMapper {
    public Command map(CommandEntity commandEntity) {
        Command command = new Command();
        command.setId(commandEntity.getId());
        command.setName(commandEntity.getName());

        return command;
    }
}
