package com.KoreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String title;
	private String body;
	private String writerName;
	private int hitCnt;
	private int point;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}
}
