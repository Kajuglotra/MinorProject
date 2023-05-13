package org.gfg.minor1.Repository;

import org.gfg.minor1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user u where u.mail = ?1", nativeQuery = true)
    public User getUserByMailId(String mail);

    @Query(value = "select u from User u where u.mail = ?1")
    public User getUserByMailIdJPQL(String mail);

    public User findByMail(String mail);

}
