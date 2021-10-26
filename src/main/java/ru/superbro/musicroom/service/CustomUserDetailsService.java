package ru.superbro.musicroom.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.superbro.musicroom.entity.AppRole;
import ru.superbro.musicroom.entity.AppUser;
import ru.superbro.musicroom.repo.RoleRepo;
import ru.superbro.musicroom.repo.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class.getName());
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public CustomUserDetailsService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepo.getByName(userName);
        if (appUser == null) {
            LOGGER.severe("User not found: " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<AppRole> appRoles = roleRepo.findRolesForUser(appUser.getId());
        List<GrantedAuthority> grantList = appRoles.stream()
                                                   .map(t -> new SimpleGrantedAuthority(t.getName()))
                                                   .collect(Collectors.toList());
        return new User(appUser.getName(), appUser.getPassword(), grantList);
    }

    private AppUser get(String name) {
        return userRepo.getByName(name);
    }


    public AppUser from(Principal principal) {
        User loggedUser = (User) ((Authentication) principal).getPrincipal();
        return get(loggedUser.getUsername());
    }

}
