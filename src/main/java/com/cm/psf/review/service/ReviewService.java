package com.cm.psf.review.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cm.psf.review.Review;
import com.cm.psf.review.dao.ReviewDao;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	ReviewDao dao;

	// ¸®ºä¸ñ·Ï
	@Override
	public List<Review> reviewPsfSearch(int psfId) {

		List<Review> reviewList = dao.reviewPsfSelect(psfId);
		/*
		if (reviewList == null) {
				System.out.println("getPsfReviews Fail!!");
		} else {
			System.out.println("psf: " + psfId);
			for (int i = 0; i < reviewList.size(); i++) {
				System.out.println("³»¿ë: " + reviewList.get(i).getReviewContent());
			}
			System.out.println("getPsfReviews Success!!");
		}
		 */
		return reviewList;
	}

	// ¸®ºäµî·Ï
	@Override
	public void reviewRegister(Review review) {
		int result = dao.reviewInsert(review);

		if (result == 0) {
			System.out.println("reviewWrite Fail!!");
		} else {
			System.out.println(review.getMember().getMemId() + ", " + review.getPsf().getPsfId() + ": "
					+ review.getReviewContent());
			System.out.println("reviewWrite Success!!");
		}
	}
	
	//³ªÀÇ ¸®ºä Á¶È¸
	@Override
	public List<Review> myReviewSearch(String memId) {
		List<Review> reviewList = dao.myReviewSelect(memId);
	
		return  reviewList;
	}
	
	//³ªÀÇ ¸®ºä »èÁ¦
	@Override
	public int reviewRemove(int reviewId) {

		int result = dao.reviewDelete(reviewId);

		if (result == 0) {
			System.out.println("Review Remove Fail!!");
		} else {
			System.out.println(reviewId);
			System.out.println("Review Remove Success!!");
		}

		return result;
	}
	
	// ¸®ºä ¼öÁ¤
	@Override
	public void reviewModify(Review review) {
		int result = dao.reviewUpdate(review);

		if (result == 0) {
			System.out.println("Review Modify Fail!!");

		} else {
			System.out.println("Review Modify Success!!");
		}


	}
}
