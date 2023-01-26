package org.example.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.example.Bean.Track;

public class TracksDao implements Dao<Track>{
	public DataSource ds;
	
	public TracksDao() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
	}
	
	public ArrayList<Track> getAll(){
		
		ArrayList<Track> tracks = new ArrayList<>();
		try {	//get all tracks from db
			
			Connection conn = ds.getConnection();
			
			//get tracks from db
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT tracks.TrackId, tracks.Name, albums.Title, genres.Name as genre, tracks.Composer FROM tracks, genres, albums WHERE tracks.AlbumId = albums.AlbumId AND genres.GenreId = tracks.GenreId ORDER BY tracks.TrackId");
			
			//add tracks to bean
			while(rs.next()){  
				//System.out.println(rs.getInt(1)+"; "+rs.getString(2)+"; "+rs.getString(3)+"; "+rs.getString(4)+"; "+rs.getString(5));
				tracks.add(new Track(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		} catch (SQLException e) {	//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return tracks;
	}
	public void add(String name,int albumid,int genreid,String composer) {
			
		int mediatype = 1;
		int milliseconds = 	343719;
		int bytes = 11170334;
		double unitprice = 4.99;
		
		
		try {
			
			Connection conn = ds.getConnection();
			
			//execute insert query
			Statement stmt=conn.createStatement();
			String query = "INSERT INTO tracks (Name, AlbumId, MediatypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) values ('"+name+"', '"+albumid+"', '"+mediatype+"', '"+genreid+"', '"+composer+"', '"+milliseconds+"', '"+bytes+"', '"+unitprice+"')";
			//System.out.println(query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} 
	}
	
	public void update(String trackid,int choice,String value) {
		try {
			
			Connection conn = ds.getConnection();
			
			
			Statement stmt=conn.createStatement();
			
			//create ad hoc query according to which information the user wants to modify
			String query = "UPDATE tracks SET ";
			switch(choice) {
				case 1:{
					query+="Name='"+value+"' WHERE TrackId="+trackid;
					break;
				}
				case 2:{
					query+="AlbumId="+value+" WHERE TrackId="+trackid;
					break;
				}
				case 3:{
					query+="GenreId="+value+" WHERE TrackId="+trackid;
					break;
				}
				case 4:{
					query+="Composer='"+value+"' WHERE TrackId="+trackid;
					break;
				}
				default:{
					query = "";
				}
			}
			//System.out.println(query);
			//execute query
			stmt.executeUpdate(query);
		}catch (SQLException e) {	//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} 
	}
	public void delete(int trackid) {
		try {
			
			Connection conn = ds.getConnection();
			
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("DELETE FROM  tracks WHERE TrackId = "+trackid);	//execute delete query
			
		}catch (SQLException e) {//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} 
	}
}
