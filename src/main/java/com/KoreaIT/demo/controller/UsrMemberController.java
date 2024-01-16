package com.KoreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.MemberService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Member;
import com.KoreaIT.demo.vo.ResultData;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		
		if (Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		
		if (Util.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요.");
		}
		
		if (Util.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요.");
		}
		
		if (Util.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요.");
		}
		
		if (Util.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			return ResultData.from("F-A", Util.f("%s은(는) 이미 사용중인 아이디입니다.", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		member = memberService.getMemberById(id);

		return ResultData.from("S-1", "회원가입 성공", member);
	}
}
