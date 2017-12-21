package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);

    }

    public static String readString() throws InterruptOperationException {
        String result = null;
        try {
            result = bis.readLine();

        } catch (IOException ignored) {

        }
        if (result.equalsIgnoreCase("exit")) throw new InterruptOperationException();
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        while (true) {
            writeMessage("Введите код валюты");
            result = readString();
            if (result==null || result.length()!=3) writeMessage("Код валюты введен неверно. Пожалуйста, повторите ввод.");
            else break;
        }
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String line;
        while (true){
            writeMessage("Введите значение номинала и количество купюр для валюты " + currencyCode);
            line = readString();
            if (line.contains(" ")) {
                String[] testArray = line.split(" ");
                if (testArray.length ==2) {
                    try {
                        int d = Integer.parseInt(testArray[0]);
                        int a = Integer.parseInt(testArray[1]);
                        if (a>0 && d>0)break;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            writeMessage("Вы ввели неверные значения, пожалуйста, повторите");
        }

        return line.split(" ");
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation result;
        while (true){
            writeMessage("Выберите необходимую операцию: \n 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            try {
                result = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                return result;
            } catch (IllegalArgumentException e) {
                writeMessage("Неверный код операции. Повторите, пожалуйста, еще раз.");
            }
        }
    }
}
