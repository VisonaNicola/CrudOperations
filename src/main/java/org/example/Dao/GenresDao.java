package org.example.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.example.Bean.Genre;

public class GenresDao implements Dao<Genre>{
	public DataSource ds;
	public GenresDao() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
	}
	@Override
	public ArrayList<Genre> getAll() {
		Connection conn;
		ArrayList<Genre> genres = new ArrayList<>();
		try {
			conn = ds.getConnection();
			//get all albums
			Statement stmt=conn.createStatement();
			ResultSet rs  = stmt.executeQuery("SELECT GenreId, Name FROM genres");
			
			
			//add genres to bean
			while(rs.next()){  
				genres.add(new Genre(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
		
	}

}
