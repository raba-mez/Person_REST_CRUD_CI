package numeryx.fr.model;

import org.springframework.security.core.userdetails.User;

public class CustomUserEntity extends User {
	
	private static final long serialVersionUID = 1L;

	public CustomUserEntity(UserEntity userEntity) {
		super(userEntity.getUsername(), userEntity.getPassword(), userEntity.getAuthorities());
	}

}
