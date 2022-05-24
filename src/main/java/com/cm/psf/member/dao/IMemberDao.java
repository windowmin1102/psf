package com.cm.psf.member.dao;

import com.cm.psf.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	int memberDelete(Member member);
	boolean isMemberId(Member member);
}