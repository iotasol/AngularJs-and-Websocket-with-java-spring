package com.demo.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.role.Role;

/**
 * Interface to handle mapping between model and database
 * 
 * @author admin
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
