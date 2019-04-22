package com.bing.pack;


public class RequestPageParam<R>{
	
	private PageInfo page = null;
	
	private R params=null;
	
	public R getParams() {
		return params;
	}

	public void setParams(R params) {
		this.params = params;
	}
	
	public PageInfo getPage() {
		if(page==null){
			page=new PageInfo();
		}
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}
}
