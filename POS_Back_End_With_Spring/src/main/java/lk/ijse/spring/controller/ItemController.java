package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.services.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseUtil getAllItems(){
        return new ResponseUtil("Ok","Successfully Loaded.!",itemService.getAllItem());
    }

    @PostMapping
    public ResponseUtil addItem(ItemDTO dto){
        itemService.addItem(dto);
        return new ResponseUtil("OK","Successfully Added.!",dto);
    }

    @DeleteMapping(params = {"code"})
    public ResponseUtil deleteItem(String code){
        itemService.deleteItem(code);
        return new ResponseUtil("OK","Successfully Deleted.!",code);
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO dto){
        itemService.updateItem(dto);
        return new ResponseUtil("OK","Successfully Update.!",dto);
    }
}
