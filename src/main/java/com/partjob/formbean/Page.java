package com.partjob.formbean;

import java.util.List;

public class Page<T> {
	
	private int pageNumber=1; //页码
	
	private int pageSize=20; //页大小
	
	private int pageCount; //总页数

    private int totalRecords; //总记录数

    private List<T> dataList; //数据
    
    public int getStart(){
    	return (pageNumber-1)*pageSize;
    }
    
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getPageNumber() {
	    if(pageNumber < 1 ){
	        pageNumber = 1;
	    }
	    if(pageNumber > getPageCount()){
	        pageNumber = getPageCount();
	    }
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
	    if(pageSize == 0){
	        pageSize = 10;
	    }
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
	    if((totalRecords%pageSize)==0){
            pageCount = totalRecords/pageSize;
        }else{
            pageCount=totalRecords/pageSize + 1;
        }
		return pageCount;
	}

	public void setPageCount(int pageCount) {
	    
		this.pageCount = pageCount;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
    
}
