package com.example.demo.service;

import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.entity.AuthInfoEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.SuchUserAlreadyExistException;
import com.example.demo.repository.AuthInfoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserRole;
import com.example.demo.mapper.CustomerSignUpRequestMapper;

import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private CustomerSignUpRequestMapper customerSignUpRequestMapper;

    @Transactional
    public void signUp(final CustomerSignUpRequest request) throws SuchUserAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final CustomerSignUpRequest request) {
        final UserEntity userEntity = customerSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.CUSTOMER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final CustomerSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }
}
