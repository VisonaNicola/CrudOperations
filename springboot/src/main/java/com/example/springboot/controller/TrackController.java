package com.example.springboot.controller;

import com.example.springboot.model.Album;
import com.example.springboot.model.Genre;
import com.example.springboot.model.Track;
import com.example.springboot.repository.AlbumsRepository;
import com.example.springboot.repository.GenresRepository;
import com.example.springboot.repository.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/track")
public class TrackController {

    @Autowired
    TracksRepository trepo;
    @Autowired
    AlbumsRepository arepo;
    @Autowired
    GenresRepository grepo;

    @GetMapping("/test")
    public ModelAndView test() {
    	return new ModelAndView("test");
    }

    @GetMapping("")
    public ModelAndView getWelcomePage() {
        return new ModelAndView("welcome");
    }

    /**
     * Add a new track to db
     */
    @PostMapping("/addTrack")
    public ModelAndView addTrack(HttpServletRequest request) {
        String name = request.getParameter("name");
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        int mediatypeid = Integer.parseInt(request.getParameter("mediaTypeId"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        String composer = request.getParameter("composer");
        int milliseconds = Integer.parseInt(request.getParameter("milliseconds"));
        int bytes = Integer.parseInt(request.getParameter("bytes"));
        double price = Double.parseDouble(request.getParameter("unitPrice"));
        Optional<Album> album =arepo.findById(albumId);
        Album newAlbum=null;
        if(album.isPresent())
            newAlbum=album.get();
        Optional<Genre> genre =grepo.findById(genreId);
        Genre newGenre=null;
        if(album.isPresent())
            newGenre=genre.get();
        Track t = new Track(name,newAlbum,mediatypeid,newGenre,composer,milliseconds,bytes,price);
        trepo.save(t);
        return new ModelAndView("redirect:/track/getTracks");
    }

    /**
     * Show the form that allow the insertion of a new track
     */
    @GetMapping("/addTrack")
    public ModelAndView getAddTrackView() {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("albums",arepo.findAll());
    	model.put("genres",grepo.findAll());
        return new ModelAndView("addtrack","model",model);
    }




    /**
     * Show a page containing all the tracks in the db
     * @return
     */
    @GetMapping("/getTracks")
    public ModelAndView getTracks() {
        Iterable<Track> tracks = trepo.findAll();

        return new ModelAndView("viewtracks","tracks",tracks);
    }


    /**
     * Get a specific track from db
     * @return
     */
    @GetMapping("/getTrack/{id}")
    public ModelAndView getTrack(@PathVariable(value="id") int id) {
    	Optional<Track> track =trepo.findById(id);
    	Track t= null;
        if(track.isPresent())
        	t=track.get();
        //System.out.println(t);
        return new ModelAndView("viewtrack","track",t);
    }




    /**
     * Show a view to update a track
     * @return
     */
    @GetMapping("/updateTrack")
    public ModelAndView getUpdateTrackView() {
        return new ModelAndView("updatetrack","tracks",trepo.findAll());
    }

    /**
     * Update a track in the db, given its trackid and the parameter to update
     */
    @PostMapping("/updateTrack")
    public ModelAndView updateTrack(HttpServletRequest request, HttpServletRequest response) {

        int id =convertTrackIdToInt(request.getParameter("trackid"));

        Optional<Track> track =trepo.findById(id);
    	Track t= null;
        if(track.isPresent())
        	t=track.get();

        int choice =Integer.parseInt(request.getParameter("choice"));

        String value = request.getParameter("value");

        switch(choice) {
            case 1:{
                t.setName(value);
                break;
            }
            case 2:{
            	Optional<Album> album =arepo.findById(Integer.parseInt(value));
            	Album a= null;
                if(album.isPresent())
                	a=album.get();
                t.setAlbum(a);
                break;
            }
            case 3:{
            	Optional<Genre> genre =grepo.findById(Integer.parseInt(value));
            	Genre g= null;
                if(genre.isPresent())
                	g=genre.get();
                t.setGenre(g);
                break;
            }
            case 4:{
                t.setComposer(value);
                break;
            }
        }
        trepo.save(t);
        return new ModelAndView("redirect:/track/getTracks");
    }



    /**
     * Show a view to delete a track
     */
    @GetMapping("/deleteTrack")
    public ModelAndView getDeleteTrackView() {
        //System.out.println(t);
        return new ModelAndView("deletetrack","tracks",trepo.findAll());
    }

    /**
     * Delete a track from db given its trackid
     */
    @PostMapping("/deleteTrack")
    public ModelAndView deleteTrack(HttpServletRequest request, HttpServletRequest response) {
    	Optional<Track> track =trepo.findById(convertTrackIdToInt(request.getParameter("trackid")));
    	Track t= null;
        if(track.isPresent())
        	t=track.get();
        trepo.delete(t);
        return new ModelAndView("redirect:/track/getTracks");
    }





    //remove '.' and ',' from track id so that it can be converted to int
    private int convertTrackIdToInt(String trackid) {
        trackid= trackid.replace(".","");
        trackid = trackid.replace(",","");

        return Integer.parseInt(trackid);
    }
}
