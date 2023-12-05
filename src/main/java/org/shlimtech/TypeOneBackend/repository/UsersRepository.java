package org.shlimtech.TypeOneBackend.repository;

import org.shlimtech.TypeOneBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

}
