package com.escuelaing.ieti.springboot.controller;

import com.escuelaing.ieti.springboot.dto.UserDto;
import com.escuelaing.ieti.springboot.entities.User;
import com.escuelaing.ieti.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService userService;

    ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@GetMapping("/api")
    @RequestMapping(method = RequestMethod.GET, value = "/api")
    public ResponseEntity<List<UserDto>> getAll() {
        System.out.println("Ingresa al @GetMapping \n");
        try {
            List<User> users = userService.getAll();
            ArrayList<UserDto> data = new ArrayList<UserDto>();
            for (User u : users) {
                data.add(modelMapper.map(u, UserDto.class));
            }
            return new ResponseEntity<List<UserDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        try {
            return new ResponseEntity<UserDto>(modelMapper.map(userService.findById(id), UserDto.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        try {
            userService.create(modelMapper.map(userDto, User.class));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id) {
        try {
            userService.update(modelMapper.map(user, User.class), id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
