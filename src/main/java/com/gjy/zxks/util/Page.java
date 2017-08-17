/**
 * 
 */
package com.gjy.zxks.util;

import java.util.Collection;

/**
 * @author panye
 * 
 */
public class Page {

	private Integer pageNo; // 当前页号 2
	private Integer pageSize; // 每页记录条数 12
	private Boolean nextPage; // 是否有下一页 true
	private Boolean prePage; // 是否有上一页 true
	private Integer totalRecNum; // 总共有多少条记录 1234
	private Integer totalPageNum;// 总共多少页 112
	private Collection pageContent; // 该页的数据(记录明细)
	private Integer startIndex; // 记录开始位置13
	private Integer endIndex; // 记录结束位置 24
	
	private Integer userStartIndex;//自定义的开始位置

	private Integer middlePageSize; //上一页和下一页中间的跳转页面的页数大小
	private Integer startPage; //开始页
	private Integer endPage;   //结束页
	
	public Page() {
		super();
		pageNo = 1;
		pageSize = 14;
		middlePageSize = 6;
	}

	public Integer getUserStartIndex() {
		return userStartIndex;
	}

	public void setUserStartIndex(Integer userStartIndex) {
		this.userStartIndex = userStartIndex;
	}



	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getNextPage() {
		return pageNo < getTotalPageNum() ? true : false;
	}

	public Boolean getPrePage() {
		return pageNo > 1 ? true : false;
	}

	public Integer getTotalRecNum() {
		return totalRecNum;
	}

	public void setTotalRecNum(Integer totalRecNum) {
		this.totalRecNum = totalRecNum;
	}

	public Integer getTotalPageNum() {
		// 9%4= 9/4+1
		return totalRecNum % pageSize > 0 ? (int) (totalRecNum / pageSize + 1)
				: (int) (totalRecNum / pageSize);
	}

	public Collection getPageContent() {
		return pageContent;
	}

	public void setPageContent(Collection pageContent) {
		this.pageContent = pageContent;
	}

	public int getStartIndex() {
		return pageSize * (pageNo - 1); // size:10 pageno:3 21
	}

	public int getEndIndex() {
		return (pageSize * pageNo > this.totalRecNum) ? (int) (this.totalRecNum
				.longValue()) : (pageSize * pageNo);
	}



	public Integer getMiddlePageSize() {
		return middlePageSize;
	}

	public void setMiddlePageSize(Integer middlePageSize) {
		this.middlePageSize = middlePageSize;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public Integer getStartPage() {
		totalPageNum = totalRecNum % pageSize > 0 ? (int) (totalRecNum / pageSize + 1)
				: (int) (totalRecNum / pageSize);
		
		int start;
		int end;
		if(totalPageNum <= middlePageSize){
			start = 1;
			end = totalPageNum;
		}else{
			int avg = middlePageSize / 2;
			start = pageNo - avg + 1;
			end =  pageNo + avg;
			if (start < 1){
				start = 1;
				end = middlePageSize;
			}
			if (end > totalPageNum){
				start = totalPageNum - middlePageSize + 1;
				end = totalPageNum;
			}
		}
		return start;
	}	

	public Integer getEndPage() {
		totalPageNum = totalRecNum % pageSize > 0 ? (int) (totalRecNum / pageSize + 1)
				: (int) (totalRecNum / pageSize);
		
		int start;
		int end;
		if(totalPageNum <= middlePageSize){
			start = 1;
			end = totalPageNum;
		}else{
			int avg = middlePageSize / 2;
			start = pageNo - avg + 1;
			end =  pageNo + avg;
			if (start < 1){
				start = 1;
				end = middlePageSize;
			}
			if (end > totalPageNum){
				start = totalPageNum - middlePageSize + 1;
				end = totalPageNum;
			}
		}
		return end;
	}
}
