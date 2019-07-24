package br.com.lubarino.jarvis.services;

import br.com.lubarino.jarvis.domains.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}