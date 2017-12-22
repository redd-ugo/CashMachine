package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(String.format("%sverifiedCards", CashMachine.RESOURCE_PATH));
    private ResourceBundle res = ResourceBundle.getBundle(String.format("%slogin_en", CashMachine.RESOURCE_PATH));

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));

            String card = ConsoleHelper.readString();
//            card.replaceAll(" ","");
            if (card.equalsIgnoreCase("exit")) throw new InterruptOperationException();
            // ConsoleHelper.writeMessage(res.getString(""));

            String pin = ConsoleHelper.readString();
            if (card.length()!=12 || pin.length()!=4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            if (validCreditCards.containsKey(card) && pin.equals(validCreditCards.getString(card))) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),card));
                break;
            } else ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),card));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));

        }


    }
}
