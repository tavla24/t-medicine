package com.milaev.medicine.board.model;

import com.milaev.medicine.board.bean.BoardDataSource;
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

@ManagedBean(name="dtLazyView")
@ViewScoped
//@SessionScoped // TODO save state in refresh
//@Stateful
public class LazyView implements Serializable {

    private LazyDataModel<ExchangeData> lazyModel;

    private ExchangeData selectedExchangeData;

    //@ManagedProperty("#{boardDataSource}")
    @Inject
    BoardDataSource boardDataSource;

    @PostConstruct
    public void init() {
        lazyModel = new LazyExchangeDataModel(boardDataSource.getDatasource());
    }

    public LazyDataModel<ExchangeData> getLazyModel() {
        return lazyModel;
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