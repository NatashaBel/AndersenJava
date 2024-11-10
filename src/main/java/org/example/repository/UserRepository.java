package org.example.repository;

import java.util.UUID;
import org.example.model.UserStatus;
import org.example.model.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
  @Modifying
  @Query("UPDATE User u SET u.userStatus = :userStatus WHERE u.id = :id")
  void updateUserStatusById(@Param("userStatus") UserStatus userStatus, @Param("id") UUID id);
}
