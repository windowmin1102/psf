package com.cm.psf.member;

public class Member {
	
	public Member() {
		
	}
	
	public Member(String memId, String memPw, String memName) {
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
	}

	private String memId;
	private String memPw;
	private String memName;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	@Override
	public String toString() {
		return "Member [memId=" + memId + ", memPw=" + memPw + ", memName=" + memName + "]";
	}
}
