package ua.goit.services;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.goit.model.User;
import ua.goit.repositories.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with that combination username and password was not found");
		}
		return new MyUserDetails(user, passwordEncoder);
	}

	public static class MyUserDetails implements UserDetails {

		private final User user;
		PasswordEncoder passwordEncoder;

		public MyUserDetails(User user, PasswordEncoder passwordEncoder) {
			this.user = user;
			this.passwordEncoder = passwordEncoder;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return user.getGroups().stream()
					.map(group -> new SimpleGrantedAuthority(group.getName()))
					.collect(Collectors.toList());
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return this.user.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
}
