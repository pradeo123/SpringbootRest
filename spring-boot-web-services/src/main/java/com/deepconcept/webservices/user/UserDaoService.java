package com.deepconcept.webservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList();
	private static int userCount = 0;

	static {
		users.add(new User(++userCount, "Prasad", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Sonu", LocalDate.now().minusYears(20)));
		users.add(new User(++userCount, "Raj", LocalDate.now().minusYears(10)));

	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {

		Predicate<User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {

		user.setId(++userCount);
		users.add(user);
		return user;
	}

}
