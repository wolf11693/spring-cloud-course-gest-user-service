package com.xantrix.webapp.model.transformer;

import org.springframework.stereotype.Component;

import com.xantrix.webapp.dto.UserInputDto;
import com.xantrix.webapp.model.User;

@Component
public class UserModelTransformer implements ModelTransformer<User, UserInputDto> {

	@Override
	public User transformFrom(UserInputDto dto) {
		User userModel = new User();
		
		userModel
			.setId(null)
			.setPassword(dto.getPassword())
			.setRuoli(dto.getRuoli());
		
		return userModel;
	}
}