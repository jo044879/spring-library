package spring.library.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.Repository.UserRepository;
import spring.library.domain.User;
import spring.library.dto.userDto.UserCreateRequest;
import spring.library.dto.userDto.UserResponse;
import spring.library.dto.userDto.UserUpdateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class UserService {

    private final UserRepository userRepository;

    public UserResponse userCreat(UserCreateRequest request) {
        User user = userRepository.save(User.user_from(request));
        return UserResponse.userResponseFrom(user);
    }

    public List<UserResponse> userRead() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::userResponseFrom)
                .toList();
    }

    public UserResponse userUpdate(UserUpdateRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.userUpdate(request);
        return UserResponse.userResponseFrom(user);
    }

    public UserResponse userDelete(Long id){
        User user =userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return UserResponse.userResponseFrom(user);
    }

}
