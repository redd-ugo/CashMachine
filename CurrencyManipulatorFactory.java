package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static volatile CurrencyManipulatorFactory mInstance;
    private static Map<String, CurrencyManipulator> map = new HashMap<>();


    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulatorFactory getInstance() {
        if (mInstance == null) {
            synchronized (CurrencyManipulatorFactory.class) {
                if (mInstance == null) {
                    mInstance = new CurrencyManipulatorFactory();
                }
            }
        }
        return mInstance;
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (map.containsKey(currencyCode.toUpperCase())) return map.get(currencyCode.toUpperCase());
        else{
            CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode.toUpperCase());
            map.put(currencyCode.toUpperCase(),manipulator);

            return manipulator;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }


}
