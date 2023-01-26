package org.example.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.example.Bean.Album;

public class AlbumsDao implements Dao<Album>{
	public DataSource ds;
	public AlbumsDao() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
	}
	@Override
	public ArrayList<Album> getAll() {
		Connection conn;
		ArrayList<Album> albums = new ArrayList<>();
		try {
			conn = ds.getConnection();
			//get all albums
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AlbumId, Title FROM albums");
			
			
			//add albums to bean
			while(rs.next()){  
				albums.add(new Album(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albums;
		
	}

}
