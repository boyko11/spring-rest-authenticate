package com.app.model;

public class Donkey {

	private String id;
	private String name;
	private String temper;
	private int age;

	public Donkey() {

	}

	public Donkey(String id, String name, String temper, int age) {
		this.id = id;
		this.name = name;
		this.temper = temper;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemper() {
		return temper;
	}

	public void setTemper(String temper) {
		this.temper = temper;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
