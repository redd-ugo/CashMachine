package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName()+".resources.";
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        //CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usd");
        /*manipulator.addAmount(50, 2);
        manipulator.addAmount(500, 1);
        manipulator.addAmount(200, 3);*/
        /*try {
            manipulator.withdrawAmount(205);
        } catch (NotEnoughMoneyException nem) {
            System.out.println("NotEnoughMoneyException");
        }*/
        //System.exit(0);
        try {
            Operation operation;
            CommandExecutor.execute(Operation.LOGIN);

            do {
                //ConsoleHelper.writeMessage("Выберите операцию: \n");
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (!operation.equals(Operation.EXIT));
        } catch (InterruptOperationException ignored) {
            try{CommandExecutor.execute(Operation.EXIT);}
            catch(InterruptOperationException e){ConsoleHelper.printExitMessage();}
        }
        ConsoleHelper.printExitMessage();
    }
}
