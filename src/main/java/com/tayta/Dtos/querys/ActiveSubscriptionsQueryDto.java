package com.tayta.Dtos.querys;

// Q8: Cantidad de suscripciones en estado activo
public class ActiveSubscriptionsQueryDto {

    private Long count;

    public ActiveSubscriptionsQueryDto() {}

    public ActiveSubscriptionsQueryDto(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
