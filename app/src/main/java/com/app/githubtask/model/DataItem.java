package com.app.githubtask.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users")
public class DataItem{

	@ColumnInfo
	@SerializedName("gender")
	private String gender;

	@ColumnInfo
	@SerializedName("name")
	private String name;

	@ColumnInfo
	@PrimaryKey
	@SerializedName("id")
	private int id;

	@ColumnInfo
	@SerializedName("email")
	private String email;

	@ColumnInfo
	@SerializedName("status")
	private String status;

	@ColumnInfo
	private String comments="";

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}