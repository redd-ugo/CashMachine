package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true){
            ConsoleHelper.writeMessage("Сколько денег вы хотите снять?");
            String sAmount = ConsoleHelper.readString();
            try {
                int amount = Integer.parseInt(sAmount);
                if (manipulator.isAmountAvailable(amount)){
                    Map<Integer,Integer> map = new TreeMap<>(Collections.reverseOrder());
                    map.putAll(manipulator.withdrawAmount(amount));
                    for (Map.Entry<Integer, Integer> entry: map.entrySet()){
                        ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(),entry.getValue()));
                    }
                    ConsoleHelper.writeMessage("Транзакция завершена успешно!");
                    break;

                }
                ConsoleHelper.writeMessage("Мы не можем выдать нужную сумму. Повторите запрос.");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Мы не можем выдать нужную сумму. Повторите запрос.");
            }catch (NumberFormatException ignored){
                ConsoleHelper.writeMessage("Что-то пошло не так. Повторите запрос.");
            }
        }
    }
}
