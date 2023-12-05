package org.shlimtech.TypeOneBackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.shlimtech.TypeOneBackend.dto.SecurityDTO;
import org.shlimtech.TypeOneBackend.dto.UserDTO;
import org.shlimtech.TypeOneBackend.jwt.JWTService;
import org.shlimtech.TypeOneBackend.mapper.UserMapper;
import org.shlimtech.TypeOneBackend.model.TypeOneUserDetails;
import org.shlimtech.TypeOneBackend.model.User;
import org.shlimtech.TypeOneBackend.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final JWTService jwtService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new TypeOneUserDetails(usersRepository.findByName(username));
    }

    @Transactional
    public UserDTO register(SecurityDTO securityDTO) {
        User user = usersRepository.save(new User(securityDTO.getName(), securityDTO.getPassword()));
        return userMapper.userToUserDTO(user);
    }

    @Transactional
    public String login(SecurityDTO securityDTO) {
        User user = usersRepository.findByName(securityDTO.getName());

        if (!user.getPassword().equals(securityDTO.getPassword())) {
            throw new RuntimeException("passwords are not equal");
        }

        String token = jwtService.generateToken(user.getId());
        return token;
    }

    @Transactional
    public User getUserById(int id) {
        return usersRepository.getReferenceById(id);
    }

    public User getCurrentUser() {
        int id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserById(id);
    }

}
