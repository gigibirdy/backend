package com.example.demo.controller;

import com.example.demo.service.CheckoutService;
import com.example.demo.service.PurchaseData;
import com.example.demo.service.PurchaseResponseData;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
// Define the base URL for all methods in this controller
@RequestMapping("/api/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // Define a method to handle http POST request that is made to /api/checkout/purchase
    @PostMapping("/purchase")
    public PurchaseResponseData checkout(@RequestBody PurchaseData purchaseData) {
        PurchaseResponseData purchaseResponseData = checkoutService.checkout(purchaseData);
        return purchaseResponseData;
    }

}
