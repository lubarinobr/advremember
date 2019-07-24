package br.com.lubarino.jarvis.repositories;

import br.com.lubarino.jarvis.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}