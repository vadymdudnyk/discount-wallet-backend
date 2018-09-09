package com.vdudnyk.discountwallet.application.user;

import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.shared.RegisterWithPhoneNumberRequest;
import com.vdudnyk.discountwallet.application.user.shared.LoginRequest;
import com.vdudnyk.discountwallet.application.user.shared.TokenResponse;
import com.vdudnyk.discountwallet.infrastructure.config.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

@Slf4j
@Service
@AllArgsConstructor
class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    void initRegistrationWithPhoneNumber(RegisterWithPhoneNumberRequest registerWithPhoneNumberRequest) {
        String phoneNumber = registerWithPhoneNumberRequest.getPhoneNumber();
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if(byPhoneNumber.isPresent()) {
            throw new ApiException("Phone number already registered");
        }
        User user = new User();
        user.setPhoneNumber(registerWithPhoneNumberRequest.getPhoneNumber());
        user.setIsVerified(false);
        user.setOneTimePassword(passwordEncoder.encode("9999"));
        user.setRoles(asSet(roleRepository.getRoleByName("ROLE_USER")));
        userRepository.save(user);
        log.info("Init registration with phone number: {}", phoneNumber);
    }

    TokenResponse authenticateUserByOneTimePassword(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhoneNumber(),
                        loginRequest.getOneTimePassword()
                )
                                                                          );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenProvider.generateToken(authentication);
        return new TokenResponse(jwtToken);
    }

    Optional<User> getUserByUsername(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
