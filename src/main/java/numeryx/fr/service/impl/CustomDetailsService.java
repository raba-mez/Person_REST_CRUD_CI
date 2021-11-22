package numeryx.fr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import numeryx.fr.dao.UserEntityDAO;
import numeryx.fr.model.CustomUserEntity;
import numeryx.fr.model.UserEntity;
import numeryx.fr.service.UserEntityService;

@Service
public class CustomDetailsService implements UserDetailsService, UserEntityService {
	
	@Autowired
	UserEntityDAO dao;
	@Autowired
	PasswordEncoder encode;

	@Override
	public CustomUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = null;
		try {
			userEntity = dao.getUserDetails(username);
			CustomUserEntity customUser = new CustomUserEntity(userEntity);
			return customUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

	@Override
	public List<UserEntity> getAllUserEntity() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		dao.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public UserEntity getByUsername(String username) {
		Optional<UserEntity> user = dao.findByUsername(username);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public UserEntity createUserEntity(UserEntity u) {
		u.setPassword(encode.encode(u.getPassword()));
		return dao.save(u);
	}

	@Override
	public void deleteUserEntity(String username) {
		dao.deleteByUsername(username);
	}
}
