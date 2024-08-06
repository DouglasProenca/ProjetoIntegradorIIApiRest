package com.sistema.apicr7imports.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO rc_user "
			+ "VALUES (:#{#c.user}, :#{#c.password}, :#{#c.mail}, :#{#c.mailPassword} , :#{#c.data})", nativeQuery = true)
	public void insert(@Param("c") User c);

	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	public User findByUsername(@Param("userName") String userName);
}