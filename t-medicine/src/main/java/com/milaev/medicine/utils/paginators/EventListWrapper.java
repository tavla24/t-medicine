package com.milaev.medicine.utils.paginators;

import com.milaev.medicine.dto.EventDTO;

import java.util.List;

public class EventListWrapper {
    private List<EventDTO> list;
    private NavigationWrapper navigation = new NavigationWrapper();

    public List<EventDTO> getList() {
        return list;
    }

    public void setList(List<EventDTO> list) {
        this.list = list;
    }

    public NavigationWrapper getNavigation() {
        return navigation;
    }

    public void setNavigation(NavigationWrapper navigation) {
        this.navigation = navigation;
    }
}
