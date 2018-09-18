package com.vdudnyk.discountwallet.application.user;

import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.shared.AuthenticateRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsMerchantRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsUserRequest;
import com.vdudnyk.discountwallet.application.user.shared.TokenResponse;
import com.vdudnyk.discountwallet.infrastructure.config.JwtTokenProvider;
import com.vdudnyk.discountwallet.infrastructure.config.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    void registerAsUser(RegisterAsUserRequest registerAsUserRequest) {
        Optional<User> byEmail = userRepository.findByEmail(registerAsUserRequest.getEmail());
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(registerAsUserRequest.getPhoneNumber());
        if (byEmail.isPresent() || byPhoneNumber.isPresent()) {
            throw new ApiException("Provided email or phone number is already taken");
        }

        User user = new User();
        user.setPhoneNumber(registerAsUserRequest.getPhoneNumber());
        user.setEmail(registerAsUserRequest.getEmail());
        user.setIsVerified(false);
        user.setPassword(passwordEncoder.encode(registerAsUserRequest.getPassword()));
        user.setRoles(asSet(roleRepository.getRoleByName("ROLE_USER")));
        userRepository.save(user);
        log.info("Registration with phone number: {}, email: {}", user.getPhoneNumber(), user.getEmail());
    }

    TokenResponse registerAsMerchant(RegisterAsMerchantRequest registerAsMerchantRequest) {
        Optional<User> byEmail = userRepository.findByEmail(registerAsMerchantRequest.getEmail());
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(registerAsMerchantRequest.getPhoneNumber());
        if (byEmail.isPresent() || byPhoneNumber.isPresent()) {
            throw new ApiException("Provided email or phone number is already taken");
        }

        User user = new User();
        user.setPhoneNumber(registerAsMerchantRequest.getPhoneNumber());
        user.setEmail(registerAsMerchantRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerAsMerchantRequest.getPassword()));
        user.setIsVerified(false);
        user.setRoles(asSet(roleRepository.getRoleByName("ROLE_MERCHANT")));
        userRepository.save(user);
        log.info("Registration with phone number: {}, email: {}", user.getPhoneNumber(), user.getEmail());
        return authenticate(new AuthenticateRequest(user.getEmail(), registerAsMerchantRequest.getPassword()));

    }

    TokenResponse authenticate(AuthenticateRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenProvider.generateToken(authentication);
        return new TokenResponse(jwtToken);
    }

    User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userRepository.findByEmail(userPrincipal.getUsername())
                             .orElseThrow(() -> new ApiException("Something went wrong " +
                                                                 "while trying to get logged user"));
    }

    User getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                             .orElseGet(() -> userRepository.findByPhoneNumber(username).orElseThrow(() -> new ApiException("Cannot find user")));
    }
}
