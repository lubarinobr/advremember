package br.com.lubarino.jarvis.repositories;

import br.com.lubarino.jarvis.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}