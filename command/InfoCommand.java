package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command{

    @Override
    public void execute() {

        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.size()<1) ConsoleHelper.writeMessage("No money available.");
        else for (CurrencyManipulator manipulator:currencyManipulators) {
            if (!manipulator.hasMoney()) ConsoleHelper.writeMessage("No money available.");
            else ConsoleHelper.writeMessage(manipulator.getCurrencyCode()+" - " + manipulator.getTotalAmount());
        }


    }
}
