package numeryx.fr.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import numeryx.fr.model.UserEntity;

@Repository
public interface UserEntityDAO extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
	void deleteByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Users u WHERE u.username = :username")
	default UserEntity getUserDetails(@Param("username") String username) {		
		return findByUsername(username).orElse(null);
	}
}
