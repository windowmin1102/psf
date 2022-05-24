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

	// �α�����
	@RequestMapping("/loginForm")
	public String loginForm(@ModelAttribute("member") Member member) {
		return "/member/loginForm";
	}

	// �α���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(@ModelAttribute("member") Member member, @ModelAttribute("ps") PubSpoFacSelect ps,
			HttpSession session, Model model) {

		Member mem = service.memberSearch(member);

		if (mem == null) {
			model.addAttribute("message", "�α��� ����: ID, PW Ȯ�����ּ���.");
			return "/member/loginForm";
		}

		session.setAttribute("member", mem);

		return "/index";
	}

	// �α׾ƿ�
	@RequestMapping("/logout")
	public String memLogout(@ModelAttribute("member") Member member, @ModelAttribute("ps") PubSpoFacSelect ps,
			HttpSession session) {

		session.invalidate(); // �α��ε� ����(����) ���������

		return "/index";
	}

	// ȸ������ ��
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}

	// ȸ������
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member, Model model, @ModelAttribute("ps") PubSpoFacSelect ps) {

		if (member.getMemId() == "") {
			model.addAttribute("message", "ȸ������ ����: ID = null");
			return "/member/joinForm";
		} else if (member.getMemName() == "") {
			model.addAttribute("message", "ȸ������ ����: NAME = null");
			return "/member/joinForm";
		} else if (member.getMemPw() == "") {
			model.addAttribute("message", "ȸ������ ����: PW = null");
			return "/member/joinForm";
		} else if (!service.memberDuplicateIdCheck(member)) {
			model.addAttribute("message", "ȸ������ ����: �ߺ� ID");
			return "/member/joinForm";
		}

		service.memberRegister(member);

		return "/index";
	}

	// ��������
	@RequestMapping("/myInfoForm")
	public String myInfoForm() {
		return "/member/myInfoForm";
	}

	// member ���� ������
	@RequestMapping(value = "/modifyForm")
	public ModelAndView modifyForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member"); // �α��ε� MEMBER ���� ���� �ҷ�����

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", service.memberSearch(member));

		mav.setViewName("/member/modifyForm");

		return mav;
	}

	// member ���� �����Ϸ�
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, @ModelAttribute("ps") PubSpoFacSelect ps, HttpServletRequest request,
			Model model) {

		HttpSession session = request.getSession();

		if (member.getMemName() == "") {
			model.addAttribute("message", "ȸ������ ���� ����: NAME = null");
			return "/member/modifyForm";
		} else if (member.getMemPw() == "") {
			model.addAttribute("message", "ȸ������ ���� ����: PW = null");
			return "/member/modifyForm";
		} else {
			Member mem = service.memberModify(member);
			session.setAttribute("member", mem); // ������ ���� ���� �ʱ�ȭ
		}

		return "/index";
	}

	// ȸ��Ż����
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		mav.addObject("member", member);
		mav.setViewName("/member/removeForm");

		return mav;
	}

	// ȸ��Ż��
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request, Model model,
			@ModelAttribute("ps") PubSpoFacSelect ps) {

		HttpSession session = request.getSession();
		int result = service.memberRemove(member);

		if (result == 0) {
			model.addAttribute("message", "ȸ��Ż�� ����: PW�� �ٸ��ϴ�.");
			return "/member/removeForm";
		}

		session.invalidate(); // �������� ����

		return "/index";
	}
}