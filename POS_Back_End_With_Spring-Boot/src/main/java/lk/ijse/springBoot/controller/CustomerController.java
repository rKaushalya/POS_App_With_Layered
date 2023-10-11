package lk.ijse.springBoot.controller;
import lk.ijse.springBoot.dto.CustomerDTO;
import lk.ijse.springBoot.services.CustomerService;
import lk.ijse.springBoot.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseUtil addCustomer(CustomerDTO dto){
        service.addCustomer(dto);
        return new ResponseUtil("Ok","Successfully Added",dto);
    }

    @DeleteMapping(params = {"cusID"})
    public ResponseUtil deleteCustomer(String cusID){
        service.deleteCustomer(cusID);
        return new ResponseUtil("Ok","Successfully Deleted",cusID);
    }

    @GetMapping
    public ResponseUtil getAllCustomer(){
        return new ResponseUtil("Ok","Successfully Loaded",service.getAllCustomer());
    }

    @GetMapping(params = {"id"})
    public ResponseUtil findCustomer(String id){
        return new ResponseUtil("Ok","Successfull", service.findCustomer(id));
    }

    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO c){
        service.updateCustomer(c);
        return new ResponseUtil("Ok","Successfully Updated",c);
    }
}
