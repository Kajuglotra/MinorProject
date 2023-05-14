package org.gfg.minor1.service;

import org.gfg.minor1.Repository.UserRepository;
import org.gfg.minor1.model.User;
import org.gfg.minor1.request.CreateUserRequest;
import org.gfg.minor1.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {
        User userFromDB=  userRepository.findByMail(createUserRequest.getEmail());
        if(userFromDB == null){
            User user = createUserRequest.toUser();
            userRepository.save(user);
            return CreateUserResponse.builder().userId(user.getId()).build();
        }
        return CreateUserResponse.builder().userId(userFromDB.getId()).build();
    }
}
