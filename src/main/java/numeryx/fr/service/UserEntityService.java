package numeryx.fr.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import numeryx.fr.model.UserEntity;


public interface UserEntityService {
	
	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	List<UserEntity> getAllUserEntity();
	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	UserEntity getByUsername(String username);
//	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	UserEntity createUserEntity(UserEntity u);
	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	void deleteUserEntity(String username);

}
