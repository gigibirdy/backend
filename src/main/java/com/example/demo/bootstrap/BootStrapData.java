package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    // Add five customers to customers table
    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer("387 Central Park Ave", "Jack", "Raven", "(876)334-9808", "87639", divisionRepository.findDivisionByDivision_name("Arizona")));
        customerRepository.save(new Customer("111 Bay Street", "Ashley", "Lee", "(987)456-7788", "98765", divisionRepository.findDivisionByDivision_name("California")));
        customerRepository.save(new Customer("787 Riverside Street", "John", "Teed", "(347)231-7711", "761230", divisionRepository.findDivisionByDivision_name("Colorado")));
        customerRepository.save(new Customer("4864 W Broad Street", "Charlotte", "Hicks", "(546)786-6633", "08721", divisionRepository.findDivisionByDivision_name("Iowa")));
        customerRepository.save(new Customer("1330 Huffman Rd", "Jason", "Holleman", "(765)091-7732", "32109", divisionRepository.findDivisionByDivision_name("Kansas")));
    }
}
