package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private static Map<Integer, ArrayList<ArrayList<Integer>>> solution = new HashMap<>();
    private Map<Integer, Integer> denominations; // Map<номинал, количество>

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (!denominations.containsKey(denomination)) denominations.put(denomination, count);
        else denominations.put(denomination, denominations.get(denomination) + count);
    }

    public int getTotalAmount() {
        int result = 0;
        for (int k : denominations.keySet()) {
            result += k * denominations.get(k);
        }
        return result;
    }

    public boolean hasMoney() {
        return denominations.size() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        List<Integer[]> banknotes;
        int sum = expectedAmount;
        Map<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);


        //if request is smaller than any available banknote f.e. 3, 17 etc.
        if (expectedAmount == 0) {
            result.put(0, 0);
            return result;
        }
        if (expectedAmount < Collections.min(temp.keySet())) throw new NotEnoughMoneyException();
        int[] notesValue = new int[temp.keySet().size()];
        int[] notesAmmount = new int[temp.keySet().size()];
        int count = 0;
        for (int key : temp.keySet()) {
            notesValue[count++] = key;
        }
        for (int i = 0; i < notesValue.length; i++) {
            notesAmmount[i] = temp.get(notesValue[i]);
        }


        banknotes = findAllPossibilities(notesValue, notesAmmount, new int[notesAmmount.length], sum, 0);
        int minAmount = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0, banknotesSize = banknotes.size(); i < banknotesSize; i++) {
            Integer[] array = banknotes.get(i);
            int x = 0;
            for (int a : array) {
                x += a;
            }
            if (x < minAmount) {
                minAmount = x;
                index = i;
            }
        }

        for (int i = 0; i < notesValue.length; i++) {
            Integer num = banknotes.get(index)[i];
            if (num > 0) {
                int key = notesValue[i];
                result.put(key, num);
                if (temp.get(key) > num) temp.put(key, temp.get(key) - num);
                else if (temp.get(key).equals(num)) temp.remove(key);
                else throw new NotEnoughMoneyException();
            }
        }

        denominations = new HashMap<>(temp);
        //denominations.putAll(temp);

        return result;
    }

    private static List<Integer[]> findAllPossibilities(int[] values, int[] ammounts, int[] variation, int price, int position) throws NotEnoughMoneyException {
        List<Integer[]> list = new ArrayList<>();
        int minValue = Arrays.stream(values).min().getAsInt();
        if (price < minValue) throw new NotEnoughMoneyException();
        int value = compute(values, variation);
        if (value < price) {
            for (int i = position; i < values.length; i++) {
                if (ammounts[i] > variation[i]) {
                    int[] newVariation = variation.clone();
                    newVariation[i]++;
                    List<Integer[]> newList = findAllPossibilities(values, ammounts, newVariation, price, i);
                    if (newList != null) {
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == price) {
            list.add(myCopy(variation));
        }

        return list;
    }

    private static int compute(int[] values, int[] variation) {
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }

    private static Integer[] myCopy(int[] ar) {
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }
}
