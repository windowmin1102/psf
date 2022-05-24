package com.cm.psf.review.dao;

import java.util.List;


import com.cm.psf.review.Review;

public interface IReviewDao {
	List<Review> reviewPsfSelect(int psfId);
	int reviewInsert(Review review);
	List<Review> myReviewSelect(String memId);
	int reviewDelete(int reviewId);
	int reviewUpdate(Review review);
}
