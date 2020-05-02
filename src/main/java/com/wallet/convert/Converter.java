package com.wallet.convert;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.util.Bcrypt;

public class Converter {

    protected User convertDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(Bcrypt.getHash(userDTO.getPassword()));
        return user;
    }

    protected UserDTO convertEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
