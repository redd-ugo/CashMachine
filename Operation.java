package com.javarush.task.task26.task2613;

public enum Operation {
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        for (Operation op: Operation.values()){
            if (op.ordinal()+1==i) return op;
        }
        throw new IllegalArgumentException();
    }
}
