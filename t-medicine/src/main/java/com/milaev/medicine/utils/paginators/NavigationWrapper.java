package com.milaev.medicine.utils.paginators;

public class NavigationWrapper {
    private boolean next = false;
    private boolean prev = false;

    private int view = 5;
    private int skip = 0;
    private long count = -1;

    private boolean nextExist = false;
    private boolean prevExist = false;

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

    public boolean isNextExist() {
        return nextExist;
    }

    public void setNextExist(boolean nextExist) {
        this.nextExist = nextExist;
    }

    public boolean isPrevExist() {
        return prevExist;
    }

    public void setPrevExist(boolean prevExist) {
        this.prevExist = prevExist;
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

        nextExist = true;
        prevExist = true;
        if (skip + view >= count)
            nextExist = false;

        if (skip - view < 0)
            prevExist = false;
    }

}
