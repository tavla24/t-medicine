package com.milaev.medicine.board.model;

import com.milaev.mq.data.ExchangeData;
import org.primefaces.model.SortOrder;

import java.lang.reflect.Field;
import java.util.Comparator;

public class LazySorter implements Comparator<ExchangeData> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(ExchangeData exchangeData1, ExchangeData exchangeData2) {
        try {
            Field privateStringField = ExchangeData.class.getDeclaredField(this.sortField);
            privateStringField.setAccessible(true);
            Object value1 = privateStringField.get(exchangeData1);
            Object value2 = privateStringField.get(exchangeData2);

//            Object value1 = ExchangeData.class.getField(this.sortField).get(exchangeData1);
//            Object value2 = ExchangeData.class.getField(this.sortField).get(exchangeData2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
