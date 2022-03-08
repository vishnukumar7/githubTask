package com.app.githubtask.model;

import com.google.gson.annotations.SerializedName;

public class Pagination{

	@SerializedName("total")
	private Integer total;

	@SerializedName("pages")
	private Integer pages;

	@SerializedName("limit")
	private Integer limit;

	@SerializedName("links")
	private Links links;

	@SerializedName("page")
	private Integer page;

	public void setTotal(Integer total){
		this.total = total;
	}

	public Integer getTotal(){
		return total;
	}

	public void setPages(Integer pages){
		this.pages = pages;
	}

	public Integer getPages(){
		return pages;
	}

	public void setLimit(Integer limit){
		this.limit = limit;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setPage(Integer page){
		this.page = page;
	}

	public Integer getPage(){
		return page;
	}
}