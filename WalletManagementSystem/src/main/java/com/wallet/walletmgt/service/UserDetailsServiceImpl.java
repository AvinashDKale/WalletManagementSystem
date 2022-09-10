package com.wallet.walletmgt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.wallet.walletmgt.entity.Role;
import com.wallet.walletmgt.entity.User;
import com.wallet.walletmgt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new MyUserDetails(user);
	}

	public void userSave(User user) {
		user.setEnabled(true);
		User tempUs = userRepository.getUserByUsername("user");
		Role rl=new Role();
		

		//user.setRoles(tempUs.getRoles());
		
		userRepository.save(user);

	}

}