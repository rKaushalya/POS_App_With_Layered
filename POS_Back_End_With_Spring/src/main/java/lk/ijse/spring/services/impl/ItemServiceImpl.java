package lk.ijse.spring.services.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.services.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addItem(ItemDTO dto) {
        if (itemRepo.existsById(dto.getCode())){
            throw new RuntimeException(dto.getCode()+ " Item is already available, please check the Code before Add.!");
        }else {
            itemRepo.save(mapper.map(dto, Item.class));
        }
    }

    @Override
    public void deleteItem(String code) {
        itemRepo.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> all = itemRepo.findAll();
        return mapper.map(all,new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public ItemDTO findItem(String code) {
        return null;
    }

    @Override
    public void updateItem(ItemDTO dto) {
        if (itemRepo.existsById(dto.getCode())){
            itemRepo.save(mapper.map(dto,Item.class));
        }else {
            throw new RuntimeException(dto.getCode()+" Item not available.!");
        }
    }
}
