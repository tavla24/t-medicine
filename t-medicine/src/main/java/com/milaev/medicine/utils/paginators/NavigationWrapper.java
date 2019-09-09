package com.milaev.medicine.utils.paginators;

public class NavigationWrapper {
    private boolean next = false;
    private boolean prev = false;

    private int view = 5;
    private int skip = 0;
    private long count = -1;

    public void setNextPage(){
        skip += view;
    }

    public void setPrevPage(){
        skip -= view;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public int getView() {
        return view;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getSkip() {
        return skip;
    }

    public void setCount(long count) {
        this.count = count;
        if (next)
            setNextPage();
        if (prev)
            setPrevPage();

        checkEdges();

        next = false;
        prev = false;
    }

    public long getCount() {
        return count;
    }

    private void checkEdges(){
        while (skip >= count)
            skip -= view;

        if (skip < 0)
            skip = 0;
    }

}
