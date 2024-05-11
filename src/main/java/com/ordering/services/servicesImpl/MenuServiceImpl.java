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
    ModelMapper modelMapper;

    @Autowired
    MenuServiceImpl(MenuRepo menuRepo,ModelMapper modelMapper) {
        this.menuRepo = menuRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        List<Menu> menus = menuRepo.findAll();
        log.info("Converting menus to DTO format");
        return menus.stream()
                .map(m->modelMapper.map(m,MenuDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto createMenu(MenuDto menu) {
        log.info("Converting MenuDTO to Menu format and creating Menu");
        return modelMapper.map(menuRepo.save(modelMapper.map(menu,Menu.class)), MenuDto.class);
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
        return modelMapper.map(menuRepo.save(menu), MenuDto.class);
    }

    @Override
    public void deleteMenuByName(String name) {
        if (!menuRepo.existsByName(name)) {
            log.error("Menu not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + name);
        }
        menuRepo.deleteByName(name);
        log.info("Menu with name: " + name + " was deleted");

    }

    @Override
    public MenuDto getMenuByName(String name) {
        if (!menuRepo.existsByName(name)) {
            log.error("Menu not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found with name: " + name);
        }
        log.info("Looking for Menu information with name: " + name);
        return modelMapper.map(menuRepo.findByName(name), MenuDto.class);
    }

}
