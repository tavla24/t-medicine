package com.milaev.medicine.dto;

import java.util.List;

public class DTOContainer <T> {

        private List<T> dtos;

        public DTOContainer(){}

        public DTOContainer(List<T> dtos) {
            this.dtos = dtos;
        }

        public List<T> getDtos() {
            return dtos;
        }

        public void setDtos(List<T> dtos) {
            this.dtos = dtos;
        }
}

