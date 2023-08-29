package com.VirtualCard.maplerads.Repository;

import com.VirtualCard.maplerads.Entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(@NotEmpty(message = "Please fill in details")
                          @Email(message = "Provide an Email address") String email);
}
