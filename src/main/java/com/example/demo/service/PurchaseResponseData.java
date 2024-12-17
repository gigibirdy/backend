package com.example.demo.service;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResponseData {
    private String orderTrackingNumber;

    public PurchaseResponseData(String trackingNumber) {
        this.orderTrackingNumber = trackingNumber;
    }
}
