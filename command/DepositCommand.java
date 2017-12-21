package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand  implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        String[] money = ConsoleHelper.getValidTwoDigits(manipulator.getCurrencyCode());
        manipulator.addAmount(Integer.parseInt(money[0]),Integer.parseInt(money[1]));

    }
}
