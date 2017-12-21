package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation;

            do {
                //ConsoleHelper.writeMessage("Выберите операцию: \n");
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (!operation.equals(Operation.EXIT));
        } catch (InterruptOperationException ignored) {
            try{CommandExecutor.execute(Operation.EXIT);}
            catch(InterruptOperationException e){ConsoleHelper.writeMessage("Спасибо за то что выбралби наш Банк!");}
        }
    }
}
