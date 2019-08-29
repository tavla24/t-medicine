package com.milaev.medicine.board.bean;

import com.milaev.mq.data.ExchangeData;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean(name = "boardDataSource")
//@ApplicationScoped
@Startup
@Singleton
public class BoardDataSource {
    private List<ExchangeData> datasource = new ArrayList<>();

    public List<ExchangeData> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<ExchangeData> datasource) {
        this.datasource = datasource;
    }
}
