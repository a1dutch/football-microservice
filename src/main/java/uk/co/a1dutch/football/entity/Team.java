package uk.co.a1dutch.football.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@SuppressWarnings("serial")
public class Team implements Serializable {

	@Id
	private String name;
	private String city;
	private String owner;
	private int capacity;
	private String competition;
	private int noOfPlayer;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateCreated;

	public Team(String name, String city, String owner, int capacity, String competition, int noOfPlayer,
			Date dateCreated) {
		this.name = name;
		this.city = city;
		this.owner = owner;
		this.capacity = capacity;
		this.competition = competition;
		this.noOfPlayer = noOfPlayer;
		this.dateCreated = dateCreated;
	}

	public Team() {
		// default constructor for jpa
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getNoOfPlayer() {
		return noOfPlayer;
	}

	public void setNoOfPlayer(int noOfPlayer) {
		this.noOfPlayer = noOfPlayer;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", city=" + city + ", owner=" + owner + ", capacity=" + capacity
				+ ", competition=" + competition + ", noOfPlayer=" + noOfPlayer + ", dateCreated=" + dateCreated + "]";
	}

}
