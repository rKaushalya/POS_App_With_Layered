package lk.ijse.springBoot.services.impl;

import lk.ijse.springBoot.dto.CustomerDTO;
import lk.ijse.springBoot.entity.Customer;
import lk.ijse.springBoot.repo.CustomerRepo;
import lk.ijse.springBoot.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    public CustomerServiceImpl(){
        System.out.println("CustomerService Constructor");
    }

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addCustomer(CustomerDTO dto) {
        if (!customerRepo.existsById(dto.getId())) {
            customerRepo.save(mapper.map(dto, Customer.class));
        }else {
            throw new RuntimeException(dto.getId()+ " Customer is already available, please check the ID before Add.!");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all,new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    @Override
    public CustomerDTO findCustomer(String id) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerDTO c) {
        if (!customerRepo.existsById(c.getId())) {
            throw new RuntimeException(c.getId()+ " Customer is not available, please check the ID before update.!");
        }
        Customer map = mapper.map(c, Customer.class);
        customerRepo.save(map);
    }
}
