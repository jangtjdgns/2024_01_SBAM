package com.KoreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.MemberService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Member;
import com.KoreaIT.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
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

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {
		
		// 로그아웃 시 이용가능
		if(session.getAttribute("loginedMemberId") != null) {
			return ResultData.from("F-L", "로그아웃 후 이용해주세요.");
		}
		
		// null 검증
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		
		if(Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		
		// 아이디가 존재하지 않는 경우
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return ResultData.from("F-3", Util.f("%s 은(는) 존재하지 않는 아이디입니다.", loginId));
		}
		
		// 아이디는 존재하지만 비밀번호가 일치하지 않는 경우
		if(!member.getLoginPw().equals(loginPw)) {
			return ResultData.from("F-4", "비밀번호를 확인해주세요.");
		}
		
		// 아이디와 비밀번호가 문제가 없을 때
		session.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-1", Util.f("%s 님 환영합니다~", member.getNickname()));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogin(HttpSession session) {
		
		// 로그인 시 이용가능
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요.");
		}
		
		// 세션 삭제
		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", "정상적으로 로그아웃 되었습니다.");
	}
}
