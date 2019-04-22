package com.bing.pack;

import java.io.Serializable;

/**
 * 用来表明分页信息
 *
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = -2736448684358714166L;

	private int pageNo = 1;
	private int pageSize = 10;
	private Long total;// 总条数
	private Integer pages;// 总页数

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public PageInfo() {
	}

	public PageInfo(int pageNo, int pageSize) {
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (this.pageNo < 1 || this.pageNo > 100)
			this.pageNo = 1;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (this.pageSize < 1 || this.pageSize > 30)
			this.pageSize = 10;
	}

	public int getStart() {
		return (pageNo - 1) * pageSize;
	}

	public int getEnd() {
		return pageNo * pageSize-1;
	}

	/**
	 * 根据总记录数， 检查需要查询的页码是否合法
	 * 
	 * @param total
	 * @return
	 */
	public boolean validPageNo(long total) {
		int page = (int) (total / this.pageSize);
		page += (total % this.pageSize > 0 ? 1 : 0);
		return this.pageNo <= page;
	}

}
