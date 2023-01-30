package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springjpa.dao.AlbumsDao;

@Controller
@RequestMapping("/album")
public class AlbumsController {
	@Autowired
	AlbumsDao adao;

	@GetMapping("/getAlbums")
	public ModelAndView getAddTrackView() {
		return new ModelAndView("getalbums","albums",adao.findAll());
	}
}
