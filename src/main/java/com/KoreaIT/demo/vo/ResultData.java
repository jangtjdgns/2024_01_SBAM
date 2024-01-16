package com.KoreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<DT> {
	private String resultCode;
	private String msg;
	private DT data;
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, DT data) {
		return new ResultData<>(resultCode, msg, data);
	}
	
	/* 
	 * 이 메서드는 롬복과 관계없이 변수가 생성되는데,
	 * 이는 boolean 타입의 메서드이기 때문에 가능함
	 * boolean 타입의 메서드명이 is로 시작될때 생성됨
	 */
	public boolean isSuccess() {
		// 결과코드가 S-로 시작되면 true 
		return this.resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		// 결과코드가 S-가 아니라면 true 
		return !isSuccess();
	}
}
