package me.maoburu.pojo;

import java.util.List;

public class Page {
	
	private List<?> rows;//所有记录
	private Long total;//总数
	
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
