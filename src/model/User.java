package model;

import java.util.ArrayList;
import java.util.List;


public class User {
	private int id;
	private long age;
	private long weight;
	private String userName;
	private String password;
	private String name;

	public User(int id, String userName, String name, long age, long weight, String password) {
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.password = password;
	}

	public int getId() {
		return this.id;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getName() {
		return this.name;
	}

	public long getAge() {
		return this.age;
	}

	public long getWeight() {
		return this.weight;
	}

	public String getPassword() {
		return this.password;
	}

}
