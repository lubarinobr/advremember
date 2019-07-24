package br.com.lubarino.jarvis.config;

import br.com.lubarino.jarvis.domains.Role;
import br.com.lubarino.jarvis.domains.User;
import br.com.lubarino.jarvis.repositories.RoleRepository;
import br.com.lubarino.jarvis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Profile("!prod")
@Component
public class DBConfig implements ApplicationListener<ContextRefreshedEvent> {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DBConfig(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = new Role();
        role.setName("Admin");

        roleRepository.save(role);

        User user = new User();
        user.setUsername("matheus.lubarino1@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("123mudar"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setName("Matheus Lubarino");

        userRepository.save(user);
    }
}
