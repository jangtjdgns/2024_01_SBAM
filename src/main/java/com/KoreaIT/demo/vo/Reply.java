package com.KoreaIT.demo.vo;

import com.KoreaIT.demo.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String body;
	
	private String writerName;
	
	public String getConvertNToBr() {
		return this.body.replaceAll("\n", "<br />");
	}
	
	public String getConvertBrToN() {
		return this.body.replaceAll("<br />", "\n");
	}
	
	// regDate
	public String getFormatRegDate() {
		   return Util.formatDate(this.regDate);
	}
	
	// updateDate
	public String getFormatUpdateDate() {
		return Util.formatDate(this.updateDate);
	}
}