package com.app.githubtask.model;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("pagination")
	private Pagination pagination;

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Pagination getPagination(){
		return pagination;
	}
}