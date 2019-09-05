package com.milaev.medicine.board.bean.settings;

import com.milaev.medicine.board.bean.settings.enums.SettingsType;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

//@Startup
@Stateful
public class BoardSettings {
    private boolean showID;
    private boolean showHealingType;
    private List<BoardSettingsItem> items;

    public boolean isShowID() {
        return showID;
    }

    public void setShowID(boolean showID) {
        this.showID = showID;
    }

    public boolean isShowHealingType() {
        return showHealingType;
    }

    public void setShowHealingType(boolean showHealingType) {
        this.showHealingType = showHealingType;
    }

    public List<BoardSettingsItem> getItems() {
        return items;
    }

    public void setItems(List<BoardSettingsItem> items) {
        this.items = items;
    }

    public void fillSettings(List<BoardSettingsItem> list) {
        items = list;

        for (BoardSettingsItem item : list) {
            switch (SettingsType.values()[item.getId()]) {
                case SHOW_ID:
                    showID = item.isShow();
                    break;
                case SHOW_H_TYPE:
                    showHealingType = item.isShow();
                    break;
            }
        }
    }

    @PostConstruct
    public void init(){
        BoardSettingsItem bsi;
        items = new ArrayList<>();
        bsi = new BoardSettingsItem(SettingsType.SHOW_ID.ordinal(), "Show id", false);
        items.add(bsi);

        bsi = new BoardSettingsItem(SettingsType.SHOW_H_TYPE.ordinal(), "Show healing type", true);
        items.add(bsi);

        fillSettings(items);
    }
}
