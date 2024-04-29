package com.ordering.controllers;

import com.ordering.dto.MenuDto;
import com.ordering.services.MenuService;
import com.ordering.validations.ErrorValidationComponent;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    MenuService menuService;
    ErrorValidationComponent errorValidationComponent;

    @Autowired
    MenuController(MenuService menuService, ErrorValidationComponent errorValidationComponent) {
        this.menuService = menuService;
        this.errorValidationComponent = errorValidationComponent;
    }
    @GetMapping
    public ResponseEntity<?> getAllMenu(){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMenuByEmail(@Valid @PathVariable String name, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Menu Search by Name: " + name);
        return new ResponseEntity<>(menuService.getMenuByName(name), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createMenu(@Valid @RequestBody MenuDto menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Creating user: " + menu.getName());
        return new ResponseEntity<>(menuService.createMenu(menu), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateMenu(@Valid @RequestBody MenuDto menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Updating menu: " + menu.getName());
        return new ResponseEntity<>(menuService.updateMenu(menu), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable String name, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Deleting user with email: " + name);
        return new ResponseEntity<>(menuService.deleteMenuByName(name), HttpStatus.OK);
    }

}
