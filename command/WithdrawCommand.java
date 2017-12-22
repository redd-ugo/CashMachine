package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(String.format("%swithdraw_en", CashMachine.RESOURCE_PATH));

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String sAmount = ConsoleHelper.readString();
            try {
                int amount = Integer.parseInt(sAmount);
                if (manipulator.isAmountAvailable(amount)){
                    Map<Integer,Integer> map = new TreeMap<>(Collections.reverseOrder());
                    map.putAll(manipulator.withdrawAmount(amount));
                    for (Map.Entry<Integer, Integer> entry: map.entrySet()){
                        ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(),entry.getValue()));
                    }
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),amount,currencyCode));
                    break;

                }else
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }catch (NumberFormatException ignored){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
    }
}
