package com.javaex.vo;

public class PersonVo {

	private int personID;
	private String name;
	private String hp;
	private String company;

	public PersonVo() {
	}

	public PersonVo(int pi) {
		this.personID = pi;
	}

	public PersonVo(String na, String hp, String cp) {
		this.name = na;
		this.hp = hp;
		this.company = cp;
	}

	public PersonVo(int pi, String na, String hp, String cp) {
		this.personID = pi;
		this.name = na;
		this.hp = hp;
		this.company = cp;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int e) {
		this.personID = e;
	}

	public String getName() {
		return name;
	}

	public void setName(String e) {
		this.name = e;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String e) {
		this.hp = e;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String e) {
		this.company = e;
	}

	@Override
	public String toString() {
		return "PhoneVo [personID= " + personID + ", name= " + name + ", hp= " + hp + ", company= " + company + "]";
	}

}
