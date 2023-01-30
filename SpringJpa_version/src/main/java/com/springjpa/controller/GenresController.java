package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springjpa.dao.GenresDao;

@Controller
@RequestMapping("/genre")
public class GenresController {
	@Autowired
	GenresDao tdao;
	
	@GetMapping("/getGenres")
	public ModelAndView getAddTrackView() {
		return new ModelAndView("getgenres","genres",tdao.findAll());
	}
}
