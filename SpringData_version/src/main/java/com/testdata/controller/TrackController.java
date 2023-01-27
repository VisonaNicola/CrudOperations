package com.testdata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.testdata.bean.Track;
import com.testdata.dao.TracksDao;

@Controller
@RequestMapping("/track")
public class TrackController {					//localhost:8080/springdata/track
	@Autowired
	private TracksDao tdao;
	
	/**
	 * Return a welcome page displaying all the possible options
	 */
	@GetMapping("")
	public String getWelcomePage() {
		return "welcome";
	}
	
	
	/**
	 * Add a new track to db
	 * @param t the track to be added
	 */
	@PostMapping("/addTrack")
	public String addTrack(@ModelAttribute("trackdata") Track t) {
		tdao.insert(t);
		System.out.println(t.getName());
		return "redirect:/track/getTracks";
		//return "forward:/track/getTracks";
	}

	/**
	 * Show the form that allow the insertion of a new track
	 */
	@GetMapping("/addTrack")
	public ModelAndView getAddTrackView() {
		return new ModelAndView("addtrack","track",new Track());
	}
	
	
	
	
	/**
	 * Show a page containing all the tracks in the db
	 * @return
	 */
	@GetMapping("/getTracks")
	public ModelAndView getTracks() {
		List<Track> t = tdao.getAll();
		//System.out.println(t);
		return new ModelAndView("viewtracks","tracks",t);
	}
	
	
	/**
	 * Get a specific track from db
	 * @return
	 */
	@GetMapping("/getTrack/{id}")
	public ModelAndView getTrack(@PathVariable(value="id") int id) {
		Track t =tdao.get(id);
		//System.out.println(t);
		return new ModelAndView("viewtrack","track",t);
	}
	
	
	
	
	/**
	 * Show a view to update a track
	 * @return
	 */
	@GetMapping("/updateTrack")
	public ModelAndView getUpdateTrackView() {
		return new ModelAndView("updatetrack","tracks",tdao.getAll());
	}
	
	/**
	 * Update a track in the db, given its trackid and the parameter to update
	 */
	@PostMapping("/updateTrack")
	public String updateTrack(HttpServletRequest request, HttpServletRequest response) {
		
		int id =convertTrackIdToInt(request.getParameter("trackid"));
		
		Track t = tdao.get(id);
		
		int choice =Integer.parseInt(request.getParameter("choice"));
		
		String value = request.getParameter("value");
		
		switch(choice) {
			case 1:{
				t.setName(value);
				break;
			}
			case 2:{
				t.setAlbumId(Integer.parseInt(value));
				break;
			}
			case 3:{
				t.setGenreId(Integer.parseInt(value));
				break;
			}
			case 4:{
				t.setComposer(value);
				break;
			}
		}
		tdao.update(t);
		
		return "redirect:/track/getTracks";
	}
	
	
	
	/**
	 * Show a view to delete a track
	 */
	@GetMapping("/deleteTrack")
	public ModelAndView getDeleteTrackView() {
		List<Track> t = tdao.getAll();
		//System.out.println(t);
		return new ModelAndView("deletetrack","tracks",t);
	}
	
	/**
	 * Delete a track from db given its trackid
	 */
	@PostMapping("/deleteTrack")
	public String deleteTrack(HttpServletRequest request, HttpServletRequest response) {
		Track t =tdao.get(convertTrackIdToInt(request.getParameter("trackid")));
		tdao.delete(t);
		return "redirect:/track/getTracks";
	}
	
	
	
	
	//remove '.' and ',' from track id so that it can be converted to int
	private int convertTrackIdToInt(String trackid) {
		trackid= trackid.replace(".","");
		trackid = trackid.replace(",","");
		
		return Integer.parseInt(trackid);
	}
	
}
