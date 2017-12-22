package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(String.format("%sexit_en", CashMachine.RESOURCE_PATH));


    @Override
    public void execute() throws InterruptOperationException{

        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String polite = ConsoleHelper.readString();
        if (polite.equalsIgnoreCase("y")) ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
