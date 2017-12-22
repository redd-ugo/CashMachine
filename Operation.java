package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        for (Operation op: Operation.values()){
            if (i == 0) throw new IllegalArgumentException();
            if (op.ordinal()==i) return op;
        }
        throw new IllegalArgumentException();
    }
}
