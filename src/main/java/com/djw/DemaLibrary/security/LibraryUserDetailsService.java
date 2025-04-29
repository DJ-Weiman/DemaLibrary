package com.djw.DemaLibrary.security;

import com.djw.DemaLibrary.domain.entities.AuthorityEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.repositories.AuthorityRepository;
import com.djw.DemaLibrary.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public LibraryUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));

        return new LibraryUserDetails(user);
    }

    public boolean userExists(String username){
        return userRepository.existsByName(username);
    }

    public LibraryUserDetails saveUser(UserEntity user, AuthorityEntity authority){
        AuthorityEntity savedAuthority = authorityRepository.save(authority);
        UserEntity savedUser = userRepository.save(user);

        return new LibraryUserDetails(savedUser);
    }
}
