package com.fintech.ticket.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

}
