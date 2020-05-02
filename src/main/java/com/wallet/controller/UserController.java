package com.wallet.controller;


import com.wallet.convert.ConvertUsers;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.response.Response;
import com.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController extends ConvertUsers {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        Response<UserDTO> response = new Response<UserDTO>();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userService.save(this.convertDtoToEntity(userDTO));

        response.setData(this.convertEntityToDto(user));


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
