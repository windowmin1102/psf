package com.cm.psf.review;

import com.cm.psf.member.Member;
import com.cm.psf.pubspofac.PubSpoFac;

public class Review {
	private int reviewId;	
	private String reviewContent;
	private double reviewGrade;
	private String reviewDate;
	private PubSpoFac psf;
	private Member member;
	private String modfiedOrNot;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public double getReviewGrade() {
		return reviewGrade;
	}
	public void setReviewGrade(double reviewGrade) {
		this.reviewGrade = reviewGrade;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public PubSpoFac getPsf() {
		return psf;
	}
	public void setPsf(PubSpoFac psf) {
		this.psf = psf;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getModfiedOrNot() {
		return modfiedOrNot;
	}
	public void setModfiedOrNot(String modfiedOrNot) {
		this.modfiedOrNot = modfiedOrNot;
	}

}
