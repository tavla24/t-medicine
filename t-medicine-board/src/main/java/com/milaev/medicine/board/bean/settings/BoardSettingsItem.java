package com.milaev.medicine.board.bean.settings;

public class BoardSettingsItem {
    private int id;
    private String title;
    private boolean show;

    public BoardSettingsItem(int id, String title, boolean show) {
        this.id = id;
        this.title = title;
        this.show = show;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
