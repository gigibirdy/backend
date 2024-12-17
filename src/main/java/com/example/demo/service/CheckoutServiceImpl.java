package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;
    public CheckoutServiceImpl(CustomerRepository customerRepository) { this.customerRepository = customerRepository;}

    @Override
    @Transactional // Atomic
    public PurchaseResponseData checkout(PurchaseData purchaseData) {
        // Get cart object from the request body
        Cart cart = purchaseData.getCart();

        // Generate a tracking number
        String trackingNumber = generateOrderTrackingNumber();

        // Set cart object property order_tracking_number with the tracking number generated
        cart.setOrder_tracking_number(trackingNumber);
        // Set the cart object property status with 'ordered'
        cart.setStatus(Status.ordered);

        // Get a list of cart item objects from the request body
        Set<CartItem> cartItems = purchaseData.getCartItems();
        // Iterate over the list and call add() method to add the item to current cart object
        cartItems.forEach(cart::add);

        // Get customer object from the request body
        Customer customer = purchaseData.getCustomer();
        // Get the phone property
        String phone = customer.getPhone();
        // Look up the customer by phone in DB
        Customer customerFromDB = customerRepository.findCustomerByPhone(phone);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }
        // Call add() method to associate customer object with the cart
        customer.add(cart);
        // Update the customer if exists in DB otherwise create new customer and add to DB
        customerRepository.save(customer);

        return new PurchaseResponseData(trackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
