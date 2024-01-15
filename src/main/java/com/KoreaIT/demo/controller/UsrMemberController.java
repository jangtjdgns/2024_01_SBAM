package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.MemberService;
import com.KoreaIT.demo.vo.Member;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		Member member = memberService.getMemberById(id);

		return member;
	}

	@RequestMapping("/usr/member/list")
	@ResponseBody
	public List<Member> showList() {

		List<Member> members = memberService.getMembers();

		return members;
	}
}
