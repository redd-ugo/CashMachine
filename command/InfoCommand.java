package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(String.format("%sinfo_en", CashMachine.RESOURCE_PATH));

    @Override
    public void execute() {

        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.size()<1) ConsoleHelper.writeMessage(res.getString("no.money"));
        else for (CurrencyManipulator manipulator:currencyManipulators) {
            if (!manipulator.hasMoney()) ConsoleHelper.writeMessage(res.getString("no.money"));
            else ConsoleHelper.writeMessage(manipulator.getCurrencyCode()+" - " + manipulator.getTotalAmount());
        }


    }
}
