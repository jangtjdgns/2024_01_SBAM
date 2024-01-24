package com.KoreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.MemberService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Member;
import com.KoreaIT.demo.vo.Rq;

@Controller
public class UsrMemberController {

	private MemberService memberService;
	private Rq rq;

	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요.");
		}
		
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");
		}
		
		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요.");
		}
		
		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요.");
		}
		
		if (Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요.");
		}
		
		if (Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 이미 사용중인 아이디입니다.", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		member = memberService.getMemberById(id);
		
		return Util.jsReplace("회원가입 되었습니다.", "/");
	}

	@RequestMapping("/usr/member/login")
	public String login() {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
				
		// 아이디가 존재하지 않는 경우
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.jsHistoryBack(Util.f("%s 은(는) 존재하지 않는 아이디입니다.", loginId));
		}
		
		// 아이디는 존재하지만 비밀번호가 일치하지 않는 경우
		if(!member.getLoginPw().equals(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요.");
		}
		
		rq.login(member);
		
		return Util.jsReplace(Util.f("%s 님 환영합니다~", member.getNickname()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.logout();
		
		return Util.jsReplace("로그아웃 되었습니다.", "/");
	}
}
