package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException{

        ConsoleHelper.writeMessage("Вы хотите добровольно покинуть наш Банк? Y/N");
        String polite = ConsoleHelper.readString();
        if (polite.equalsIgnoreCase("y")) ConsoleHelper.writeMessage("Спасибо за то что выбралби наш Банк!");
    }
}
