package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.ReservationDef;

public class ReservationService {
    ReservationDef rs = new ReservationDef();
    public String defineReservtion(ReservationDef r){
        return rs.defineReservtion(r);
    }
}
