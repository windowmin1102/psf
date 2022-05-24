package com.cm.psf.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cm.psf.member.Member;
import com.cm.psf.member.service.MemberService;
import com.cm.psf.pubspofac.PubSpoFacSelect;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;

	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	// 로그인폼
	@RequestMapping("/loginForm")
	public String loginForm(@ModelAttribute("member") Member member) {
		return "/member/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(@ModelAttribute("member") Member member, @ModelAttribute("ps") PubSpoFacSelect ps,
			HttpSession session, Model model) {

		Member mem = service.memberSearch(member);

		if (mem == null) {
			model.addAttribute("message", "로그인 실패: ID, PW 확인해주세요.");
			return "/member/loginForm";
		}

		session.setAttribute("member", mem);

		return "/index";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String memLogout(@ModelAttribute("member") Member member, @ModelAttribute("ps") PubSpoFacSelect ps,
			HttpSession session) {

		session.invalidate(); // 로그인된 정보(세션) 끊어버리기

		return "/index";
	}

	// 회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member, Model model, @ModelAttribute("ps") PubSpoFacSelect ps) {

		if (member.getMemId() == "") {
			model.addAttribute("message", "회원가입 실패: ID = null");
			return "/member/joinForm";
		} else if (member.getMemName() == "") {
			model.addAttribute("message", "회원가입 실패: NAME = null");
			return "/member/joinForm";
		} else if (member.getMemPw() == "") {
			model.addAttribute("message", "회원가입 실패: PW = null");
			return "/member/joinForm";
		} else if (!service.memberDuplicateIdCheck(member)) {
			model.addAttribute("message", "회원가입 실패: 중복 ID");
			return "/member/joinForm";
		}

		service.memberRegister(member);

		return "/index";
	}

	// 나의정보
	@RequestMapping("/myInfoForm")
	public String myInfoForm() {
		return "/member/myInfoForm";
	}

	// member 정보 수정폼
	@RequestMapping(value = "/modifyForm")
	public ModelAndView modifyForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member"); // 로그인됨 MEMBER 세션 정보 불러오기

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", service.memberSearch(member));

		mav.setViewName("/member/modifyForm");

		return mav;
	}

	// member 정보 수정완료
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, @ModelAttribute("ps") PubSpoFacSelect ps, HttpServletRequest request,
			Model model) {

		HttpSession session = request.getSession();

		if (member.getMemName() == "") {
			model.addAttribute("message", "회원정보 수정 실패: NAME = null");
			return "/member/modifyForm";
		} else if (member.getMemPw() == "") {
			model.addAttribute("message", "회원정보 수정 실패: PW = null");
			return "/member/modifyForm";
		} else {
			Member mem = service.memberModify(member);
			session.setAttribute("member", mem); // 수정된 정보 세션 초기화
		}

		return "/index";
	}

	// 회원탈퇴폼
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		mav.addObject("member", member);
		mav.setViewName("/member/removeForm");

		return mav;
	}

	// 회원탈퇴
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request, Model model,
			@ModelAttribute("ps") PubSpoFacSelect ps) {

		HttpSession session = request.getSession();
		int result = service.memberRemove(member);

		if (result == 0) {
			model.addAttribute("message", "회원탈퇴 실패: PW가 다릅니다.");
			return "/member/removeForm";
		}

		session.invalidate(); // 세션정보 삭제

		return "/index";
	}
}