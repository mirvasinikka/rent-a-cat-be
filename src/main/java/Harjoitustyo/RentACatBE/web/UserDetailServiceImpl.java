package Harjoitustyo.RentACatBE.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final AppUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		AppUser currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
				AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
	
}

