package com.app.githubtask.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private String next;

	@SerializedName("current")
	private String current;

	@SerializedName("previous")
	private String previous;

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setCurrent(String current){
		this.current = current;
	}

	public String getCurrent(){
		return current;
	}

	public void setPrevious(String previous){
		this.previous = previous;
	}

	public String getPrevious(){
		return previous;
	}
}