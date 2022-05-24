package com.cm.psf.member.service;

import com.cm.psf.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
	boolean memberDuplicateIdCheck(Member member);
}