package com.ordering.services;


import com.ordering.dto.MenuDto;
import com.ordering.dto.UserDto;

import java.util.List;

public interface MenuService {

    List<?> getAllMenus();
    MenuDto createMenu(MenuDto user);
    MenuDto updateMenu(MenuDto user);
    void deleteMenuByName(String name);
    MenuDto getMenuByName(String name);
}
