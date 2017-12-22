package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand  implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(String.format("%sdeposit_en", CashMachine.RESOURCE_PATH));

    @Override
    public void execute() throws InterruptOperationException {

        while (true) {
            String currencyCode = ConsoleHelper.askCurrencyCode();
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            String[] money = ConsoleHelper.getValidTwoDigits(manipulator.getCurrencyCode());
            ConsoleHelper.writeMessage(res.getString("before"));
            int denomination = 0;
            int count = 0;
            try {
                denomination = Integer.parseInt(money[0]);
                count = Integer.parseInt(money[1]);
                manipulator.addAmount(denomination, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),denomination*count,currencyCode));
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }


    }
}
