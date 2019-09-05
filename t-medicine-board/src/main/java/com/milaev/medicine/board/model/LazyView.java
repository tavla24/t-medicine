package com.milaev.medicine.board.model;

import com.milaev.medicine.board.bean.BoardDataSource;
import com.milaev.medicine.board.bean.settings.BoardSettings;
import com.milaev.mq.data.ExchangeData;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name="dtLazyView")
@ViewScoped
//@SessionScoped // TODO save state in refresh
//@Stateful
public class LazyView implements Serializable {

    private LazyDataModel<ExchangeData> lazyModel;
    private LazyDataModel<ExchangeData> lazyModelToday;

    private ExchangeData selectedExchangeData;

    //@ManagedProperty("#{boardDataSource}")
    @Inject
    private BoardDataSource boardDataSource;

    @Inject
    private BoardSettings settings;

    @PostConstruct
    public void init() {
        lazyModel = new LazyExchangeDataModel(boardDataSource.getDatasource());
        lazyModelToday = new LazyExchangeDataModel(boardDataSource.getDatasource(new Date()));
    }

    public LazyDataModel<ExchangeData> getLazyModel() {
        return lazyModel;
    }

    public LazyDataModel<ExchangeData> getLazyModelToday() {
        return lazyModelToday;
    }

    public BoardSettings getSettings() {
        return settings;
    }

    public ExchangeData getSelectedExchangeData() {
        return selectedExchangeData;
    }

    public void setSelectedExchangeData(ExchangeData selectedExchangeData) {
        this.selectedExchangeData = selectedExchangeData;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("ExchangeData Selected", ((ExchangeData) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}