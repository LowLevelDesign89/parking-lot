package com.app.parkinglot.service.strategy;

import com.app.parkinglot.models.entity.Ticket;

public interface PaymentStrategy {
    void calculatePayment(Ticket ticket);
}
