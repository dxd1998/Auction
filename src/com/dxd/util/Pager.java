package com.dxd.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pager implements Serializable{
	private int currentPage;//当前�?
	private int rowCount;//总条�?
	private int rowPerPage = 5;//每页显示条数
	private int pageCount;//总页�?
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Pager(int rowCount, int rowPerPage, int currentPage) {
		this.rowCount = rowCount;
		this.rowPerPage = rowPerPage;
		this.currentPage = currentPage;
		if(this.rowCount % this.rowPerPage == 0){
			this.pageCount = this.rowCount / this.rowPerPage;
		}else if(this.rowCount % this.rowPerPage > 0){
			this.pageCount = this.rowCount / this.rowPerPage + 1;
		}else{
			this.pageCount = 0;
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}
}
