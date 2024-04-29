package com.ordering.services.servicesImpl;

import com.ordering.documents.Menu;
import com.ordering.dto.MenuDto;
import com.ordering.repositories.MenuRepo;
import com.ordering.services.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    MenuRepo menuRepo;

    @Autowired
    MenuServiceImpl(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    @Override
    public List<?> getAllMenus() {
        List<Menu> menus = menuRepo.findAll();
        log.info("Converting menus to DTO format");
        return menus.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto createMenu(MenuDto menu) {
        log.info("Converting MenuDTO to Menu format and creating user");
        return this.convertToDTO(menuRepo.save(this.convertToDocument(menu)));
    }

    @Override
    public MenuDto updateMenu(MenuDto menuDto) {
        if (!menuRepo.existsByName(menuDto.getName())) {
            log.error("User not found with email: " + menuDto.getName());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found with name: " + menuDto.getName());
        }
        Menu menu = menuRepo.findByName(menuDto.getName());
        log.info("Updating Menu fields");
        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());
        menu.setPrice(menuDto.getPrice());
        menu.setAvailability_Status(menuDto.getAvailability_Status());
        return this.convertToDTO(menuRepo.save(menu));
    }

    @Override
    public String deleteMenuByName(String name) {
        if (!menuRepo.existsByName(name)) {
            log.error("Menu not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + name);
        }
        menuRepo.deleteByName(name);
        log.info("Menu with name: " + name + " was deleted");
        return "Menu with name: " + name + " was deleted";

    }

    @Override
    public MenuDto getMenuByName(String name) {
        if (!menuRepo.existsByName(name)) {
            log.error("Menu not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found with name: " + name);
        }
        log.info("Looking for Menu information with name: " + name);
        return this.convertToDTO(menuRepo.findByName(name));
    }


    private MenuDto convertToDTO(Menu menu) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(menu, MenuDto.class);
    }

    private Menu convertToDocument(MenuDto menu) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(menu, Menu.class);
    }
}
