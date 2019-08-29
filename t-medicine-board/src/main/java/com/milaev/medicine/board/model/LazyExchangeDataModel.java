package com.milaev.medicine.board.model;

import com.milaev.mq.data.ExchangeData;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.lang.reflect.Field;
import java.util.*;

public class LazyExchangeDataModel extends LazyDataModel<ExchangeData> {

    private List<ExchangeData> datasource;

    public LazyExchangeDataModel(List<ExchangeData> datasource) {
        this.datasource = datasource;
    }

    @Override
    public ExchangeData getRowData(String rowKey) {
        for(ExchangeData exchangeData : datasource) {
            if(exchangeData.getId().equals(rowKey))
                return exchangeData;
        }

        return null;
    }

    @Override
    public Object getRowKey(ExchangeData exchangeData) {
        return exchangeData.getId();
    }

    @Override
    public List<ExchangeData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<ExchangeData> data = new ArrayList<>();

        for(ExchangeData exchangeData : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
//                        String fieldValue = String.valueOf(exchangeData.getClass().getField(filterProperty).get(exchangeData));

                        Field privateStringField = exchangeData.getClass().getDeclaredField(filterProperty);
                        privateStringField.setAccessible(true);
                        String fieldValue = String.valueOf(privateStringField.get(exchangeData));

                        if(filterValue == null || fieldValue.toLowerCase().contains(filterValue.toString().toLowerCase()))
                            match = true;
                        else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(exchangeData);
            }
        }

        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        int dataSize = data.size();
        this.setRowCount(dataSize);

        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
