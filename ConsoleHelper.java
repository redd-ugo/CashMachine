package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(String.format("%scommon_en", CashMachine.RESOURCE_PATH));

    public static void writeMessage(String message){
        System.out.println(message);

    }

    public static String readString() throws InterruptOperationException {
        String result = null;
        try {
            result = bis.readLine();

        } catch (IOException ignored) {

        }
        if (result.equalsIgnoreCase("exit")) {
            writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            result = readString();
            if (result==null || result.length()!=3) writeMessage(res.getString("invalid.data"));
            else break;
        }
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String line;
        while (true){
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
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
            //writeMessage("Вы ввели неверные значения, пожалуйста, повторите");
        }

        return line.split(" ");
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation result;
        while (true){
            writeMessage(String.format("%s \n 1 - %s, 2 - %s, 3 - %s, 4 - %s",res.getString("choose.operation"),
                    res.getString("operation.INFO"),res.getString("operation.DEPOSIT"),
                    res.getString("operation.WITHDRAW"),res.getString("operation.EXIT")));
            try {
                result = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                return result;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
