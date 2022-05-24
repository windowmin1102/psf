package com.cm.psf.review.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cm.psf.member.Member;
import com.cm.psf.review.Review;
import com.cm.psf.review.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	ReviewService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	// 리뷰 작성 폼
	@RequestMapping(value = "/reviewForm", method = RequestMethod.GET)
	public String reviewForm(HttpServletRequest httpServletRequest, Model model, Review review) {

        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");
        model.addAttribute("reviewId", id);
        model.addAttribute("reviewName", name);
		
		return "/review/reviewForm";
	}
	
	// 리뷰 작성
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
	public String reviewWrite( Model model, Review review) {
		
		if(review.getReviewGrade() == 0) {
			model.addAttribute("message", "평점을 입력해 주세요.");
			return "/review/reviewForm";
		} else if(review.getReviewContent() == "") {
			model.addAttribute("message", "내용을 입력해 주세요.");
			return "/review/reviewForm";
		}

		service.reviewRegister(review);
		model.addAttribute("message", "success");
		
		return "/review/reviewForm";
	}
	
	// 마커 클릭 시, 해당 마커의 id값을 가져와 그에 맞는 리스트를 리턴 시켜준다.
	@RequestMapping(value = "AttachPsfList", method = RequestMethod.POST)
	@ResponseBody
	public List<Review> AttachRegister(@RequestBody int psfId ){
		List<Review> rvs = service.reviewPsfSearch(psfId);
		return rvs; 
	}
	
	// 내가 쓴 리뷰 목록
	@RequestMapping(value = "/myReviewForm", method = RequestMethod.GET)
	public ModelAndView myReviewForm(HttpServletRequest httpServletRequest, Review review) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = httpServletRequest.getSession();
		Member member = (Member) session.getAttribute("member");
		
		List<Review> myReviews = service.myReviewSearch(member.getMemId());
		
		mav.addObject("reviews", myReviews);
		mav.setViewName("/review/myReviewForm");

		return mav;
	}
	
	// 나의 리뷰 삭제
	@RequestMapping(value = "/reviewRemove", method = RequestMethod.GET)
	public String reviewRemove(HttpServletRequest httpServletRequest) {
		String rid = httpServletRequest.getParameter("rid");
		int result = service.reviewRemove(Integer.parseInt(rid));
		if(result == 0) {
			
		}
		return "redirect:/review/myReviewForm";
	}
	
	
	// 리뷰 수정폼
	@RequestMapping(value = "/reviewModifyForm", method = RequestMethod.POST)
	public String reviewRemove(Review review) {
		
		return "/review/reviewModifyForm";
	}
	
	
	// 나의 리뷰 수정
	@RequestMapping(value = "/reviewModify", method = RequestMethod.POST)
	public String modify(Model model, Review review) {
		if(review.getReviewGrade() == 0) {
			model.addAttribute("message", "평점을 입력해 주세요.");
			return "/review/reviewModifyForm";
		} else if(review.getReviewContent() == "") {
			model.addAttribute("message", "내용을 입력해 주세요.");
			return "/review/reviewModifyForm";
		}
		
		service.reviewModify(review);
		
		return "redirect:/review/myReviewForm";
	}
}