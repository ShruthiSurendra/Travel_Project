package com.mphasis.travel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TravelDaoImpl {
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
	
	private Connection getConn() throws ClassNotFoundException, SQLException
	{
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String url, driver, username, password;
		driver=rb.getString("driver");
		url=rb.getString("url");
		username=rb.getString("username");
		password=rb.getString("password");
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	}
	public int create(Travel travel) throws ClassNotFoundException, SQLException
	{
		Connection con=getConn();
		PreparedStatement st=con.prepareStatement("INSERT INTO TRAVEL VALUES(?,?,?,?,?)");
		st.setInt(1, travel.getTravelId());
		st.setString(2,travel.getPlaceName());
		st.setString(3,sdf.format(travel.getDateOfJourney()));
		st.setInt(4,travel.getPricePackage());
		st.setBytes(5,travel.getPlaceImage());
		int no=st.executeUpdate(); 
		return no ;
	}
	public List<Travel>read() throws ClassNotFoundException, SQLException, ParseException
	{
		Connection con=getConn();
		PreparedStatement st=con.prepareStatement("SELECT * FROM TRAVEL");
		ResultSet rs=st.executeQuery();
		List<Travel>travelList=new ArrayList<Travel>();
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next())
		{
			Travel travel=new Travel(rs.getInt(1),rs.getString(2),sdf1.parse(rs.getString(3)),rs.getInt(4),rs.getBytes(5));
			travelList.add(travel);
		}
		
		return travelList ;
	}
	
public Travel read(int travelId) throws ClassNotFoundException, SQLException, ParseException
{
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Connection con =getConn();
	PreparedStatement st= con.prepareStatement("SELECT * FROM TRAVEL WHERE TRAVEL_ID=?");
	st.setInt(1, travelId);
	ResultSet rs=st.executeQuery();
	Travel travel=null;
	if(rs.next())
		 travel=new Travel(rs.getInt(1),rs.getString(2),sdf1.parse(rs.getString(3)),rs.getInt(4),rs.getBytes(5));
	
	return travel;
}
public int update(Travel travel) throws ClassNotFoundException, SQLException {
	Connection con=getConn();
	PreparedStatement st=con.prepareStatement("UPDATE TRAVEL SET PLACE_NAME=?,DATE_OF_JOURNEY=?,PRICE_PACKAGE=?,PLACE_IMAGE=? WHERE TRAVEL_ID=?");
	st.setString(1,travel.getPlaceName());
	st.setString(2,sdf.format(travel.getDateOfJourney()));
	st.setInt(3,travel.getPricePackage());
	st.setBytes(4,travel.getPlaceImage());
	st.setInt(5, travel.getTravelId());
	int no=st.executeUpdate(); 
	return no ;
}
public int delete(int travelId) throws ClassNotFoundException, SQLException {
	Connection con=getConn();
	PreparedStatement st=con.prepareStatement("DELETE FROM TRAVEL WHERE TRAVEL_ID=?");
	st.setInt(1, travelId);
	int no=st.executeUpdate();
	return no;
}
}
