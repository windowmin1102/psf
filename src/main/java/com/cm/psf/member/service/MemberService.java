package com.cm.psf.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cm.psf.member.Member;
import com.cm.psf.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {

	@Autowired
	MemberDao dao;

	// 회원가입
	@Override
	public void memberRegister(Member member) {
		int result = dao.memberInsert(member);

		if (result == 0) {
			System.out.println("Join Fail!!");
		} else {
			System.out.println(member.toString());
			System.out.println("Join Success!!");
		}
	}

	// 로그인
	@Override
	public Member memberSearch(Member member) {

		Member mem = dao.memberSelect(member);

		if (mem == null) {
			System.out.println("Login Fail!!");
		} else {
			System.out.println(mem.toString());
			System.out.println("Login Success!!");
		}

		return mem;
	}

	// 회원 수정
	@Override
	public Member memberModify(Member member) {
		int result = dao.memberUpdate(member);

		if (result == 0) {
			System.out.println("Modify Fail!!");
			return null;
		} else {
			System.out.println(member.toString());
			System.out.println("Modify Success!!");
		}

		return member;
	}

	// 회원탈퇴
	@Override
	public int memberRemove(Member member) {
		String memId = member.getMemId();
		int result = dao.memberDelete(member);

		if (result == 0) {
			System.out.println("Remove Fail!!");
		} else {
			System.out.println(memId);
			System.out.println("Remove Success!!");
		}

		return result;
	}

	// 아이디 중복검사
	@Override
	public boolean memberDuplicateIdCheck(Member member) {
		System.out.println(dao.isMemberId(member));

		return dao.isMemberId(member);
	}
}