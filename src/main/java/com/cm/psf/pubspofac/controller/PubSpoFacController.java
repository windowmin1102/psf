package com.cm.psf.pubspofac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;
import com.cm.psf.pubspofac.service.PubSpoFacService;

@Controller
@RequestMapping("/pubspofac")
public class PubSpoFacController {

	@Autowired
	PubSpoFacService pubSpoFacService;

	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/PsfDatas", method = {RequestMethod.GET, RequestMethod.POST})
	public String test(@ModelAttribute("ps") PubSpoFacSelect ps, Model model) {
		
		
		if (ps.getpSelectOne() == null && ps.getpSelectTwo() == null) {
			return "redirect:/";
		}

		List<PubSpoFac> psfs = pubSpoFacService.pubSpoFacSearch(ps);
		
		if(psfs.isEmpty()) {
			return "redirect:/";
		}
		
		model.addAttribute("psfs", psfs);
		
		return "/index";
	}
}
