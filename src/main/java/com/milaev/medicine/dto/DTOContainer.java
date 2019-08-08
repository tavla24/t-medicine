package com.milaev.medicine.dto;

import java.util.List;

public class DTOContainer <T> {

        private List<T> list;

        public DTOContainer(){}

        public DTOContainer(List<T> list) {
            this.list = list;
        }

        public List<T> getDtos() {
            return list;
        }

        public void setDtos(List<T> list) {
            this.list = list;
        }
}

