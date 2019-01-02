package com.pcatalog.pcatalog.services.users;

import com.pcatalog.pcatalog.dtos.UserDto;
import com.pcatalog.pcatalog.models.Role;
import com.pcatalog.pcatalog.models.User;
import com.pcatalog.pcatalog.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		Role role = new Role();
		role.setName("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		newUser.setRoles(roles);
        return userDao.save(newUser);
    }

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		User newUser = new User();
		newUser.setUsername("admin");
		newUser.setPassword(bcryptEncoder.encode("admin"));
		Role role = new Role();
		role.setName("ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		newUser.setRoles(roles);
		userDao.save(newUser);

		User newUser2 = new User();
		newUser2.setUsername("user");
		newUser2.setPassword(bcryptEncoder.encode("user"));
		Role role2 = new Role();
		role2.setName("USER");
		Set<Role> roles2 = new HashSet<>();
		roles2.add(role2);
		newUser2.setRoles(roles2);
		userDao.save(newUser2);
	}
}
