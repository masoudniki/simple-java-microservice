package com.masoud.customerservice.customer;

import com.masoud.customerservice.customer.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));

        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("can not update customer:: no customer found with provided id:: %s",request.id())
        ) );

        mergeCustomer(request,customer);

        customerRepository.save(customer);
    }


    private void mergeCustomer(CustomerRequest request, Customer customer) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getCustomers() {
       return  customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }
    public CustomerResponse getCustomer(String id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow( () -> new CustomerNotFoundException("There is not any customer with given information."));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
