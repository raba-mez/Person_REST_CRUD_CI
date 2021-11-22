package numeryx.fr.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import numeryx.fr.model.ApiResponse;
import numeryx.fr.model.UserEntity;
import numeryx.fr.service.UserEntityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserEntityController {
	
	@Autowired
	UserEntityService service;
	
	@GetMapping
	public ApiResponse<List<UserEntity>> getAllUsers(){
		return new ApiResponse<List<UserEntity>>(HttpStatus.OK.value(), "List fetched successfully", service.getAllUserEntity());
	}
	
	@GetMapping("/{username}")
	public ApiResponse<UserEntity> getByUsername(@PathVariable String username){
		return new ApiResponse<UserEntity>(HttpStatus.OK.value(), "User fetched successfully", service.getByUsername(username));
	}
	
	@PostMapping()
	public ApiResponse<UserEntity> createUser(@RequestBody UserEntity u){
		Preconditions.checkNotNull(u);
		return new ApiResponse<UserEntity>(HttpStatus.OK.value(), "User created successfully", service.createUserEntity(u));
	}
	
	@PutMapping("/{username}")
	public ApiResponse<UserEntity> updateUserEntity(@PathVariable("username") String username, @RequestBody UserEntity u){
		Preconditions.checkNotNull(u);
		Preconditions.checkNotNull(username);
		UserEntity user = service.getByUsername(username);
		if(Objects.isNull(user))
			return new ApiResponse<UserEntity>(HttpStatus.NOT_FOUND.value(), "User with username "+username+" not found", null);
		u.setIdUser(user.getIdUser());
		return 
				new ApiResponse<UserEntity>(
						HttpStatus.OK.value(), "User updated successfully", service.createUserEntity(u));
	}
	
	@DeleteMapping("/{username}")
	public ApiResponse<Void> deteleUserEntity(@PathVariable String username){
		service.deleteUserEntity(username);
		return new ApiResponse<Void>(HttpStatus.OK.value(), "User deteleted successfully", null);
	}

}
