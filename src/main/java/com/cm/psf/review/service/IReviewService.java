package com.cm.psf.review.service;

import java.util.List;
import com.cm.psf.review.Review;

public interface IReviewService {
	List<Review> reviewPsfSearch(int psfId);
	void reviewRegister(Review review);
	List<Review> myReviewSearch(String memId);
	int reviewRemove(int reviewId);
	void reviewModify(Review review);
}
