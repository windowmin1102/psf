package com.cm.psf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cm.psf.member.Member;
import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;


@Controller
public class HomeController {
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("ps") PubSpoFacSelect ps, Model model) {
		List<PubSpoFac> psfs = null;
		model.addAttribute("psfs", psfs);

		return "/index";
	}

}
