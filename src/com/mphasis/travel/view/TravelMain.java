package com.mphasis.travel.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.mphasis.travel.model.*;
public class TravelMain {

	public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		TravelDaoImpl tdao=new TravelDaoImpl();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		Scanner sc=new Scanner(System.in);
		do
		{
			int travelId;
			 String placeName="";
			 Date dateOfJourney=null;
			 int pricePackage;
			 byte[] placeImage=null;
			Travel travel=null;
			System.out.println("	1) Add travel details\r\n" + 
					"	2) display the travels details\r\n" + 
					"	3) display detais by travelid\r\n" + 
					"	4) update the detaisl\r\n" + 
					"	5) delete an entry\r\n" + 
					"	6) Exit\r\n" + 
					"");
			int choice=sc.nextInt();
			switchBlock:
			switch(choice)
			{
			case 1:			//add
				System.out.println("Please provide the details ");
				System.out.println("Travel Id:");
				travelId=sc.nextInt();
				System.out.println("Place to travel:");
				placeName=sc.nextLine();
				if(placeName.equals(""))
					placeName=sc.nextLine();
				System.out.println("Date of Journey: (dd-MMM-yy)");
				dateOfJourney=sdf.parse(sc.nextLine());
				System.out.println("price:");
				pricePackage=sc.nextInt();
				sc.nextLine();
				System.out.println("Picture: (pls enter the full path)");
				File f=new File(sc.nextLine());
				int len=(int)f.length();
				placeImage=new byte[len];
				FileInputStream fis=new FileInputStream(f);
				fis.read(placeImage);
				fis.close();
				travel=new Travel(travelId,placeName,dateOfJourney,pricePackage,placeImage);
				tdao.create(travel);
				System.out.println("Added...");
				
				break;
			case 2:///display details
//				System.out.format("%d %-10s %-10s %d %10s\n");
				List<Travel> travels = tdao.read();
                for(Travel t : travels)
//                    System.out.format("%d %-10s %-10s %d %15s\n", t.getTravelId(),t.getPlaceName(),t.getDateOfJourney(),t.getPricePackage(),t.getPlaceImage());
				break;
				
			case 3://display by id
				System.out.println("Enter the travelId to view the details");
				int tid=sc.nextInt();
				travel=tdao.read(tid);
//				 System.out.format("%d %-10s %-10s %d %15s\n", travel.getTravelId(),travel.getPlaceName(),travel.getDateOfJourney(),travel.getPricePackage(),travel.getPlaceImage());
				System.out.println(travel);
				break;
				
				
			case 4://update
				System.out.println("Please provide the values to update the details");
				System.out.println("travelId Id:(mandatory)");
				try {
				travelId=sc.nextInt();
				}catch(InputMismatchException ime)
				{
					System.out.println("Please enter only valid travel Id");
					continue;
				}
				System.out.println("placeName:(leave blank to keep unchanged)");
				placeName=sc.nextLine();
				if(placeName.equals(""))
					placeName=sc.nextLine();
				System.out.println("Date of Journey: (dd-MMM-yy)(leave blank to keep unchanged)");
				String sdate=sc.nextLine();
				if(!sdate.equals(""))
					dateOfJourney=sdf.parse(sdate);
				System.out.println("price:(leave blank to keep unchanged)");
				pricePackage=sc.nextInt();
				sc.nextLine();
				System.out.println("Picture: (pls enter the full path)(leave blank to keep unchanged)");
				String sFile=sc.nextLine();
				if(!sFile.equals(""))
				{	
					f=new File(sFile);
					len=(int)f.length();
					placeImage=new byte[len];
					fis=new FileInputStream(f);
					fis.read(placeImage);
					fis.close();
				}
				
				
				travel=tdao.read(travelId);
				if(travel==null)
				{
					System.out.println("Sorry, No associate found for id:"+travelId);
					break switchBlock;
				}
				//update whichever field is entered by the user
				if(!placeName.equals(""))
					travel.setPlaceName(placeName);
				if(dateOfJourney!=null)
					travel.setDateOfJourney(dateOfJourney);
				if(pricePackage!=0)
					travel.setPricePackage(pricePackage);
				if(placeImage!=null)
					travel.setPlaceImage(placeImage);
				int no=tdao.update(travel);
				System.out.println(no+" row(s) updated");
				break;
			case 5://delete
				System.out.println("provide an id to delete the details ");
				tid=sc.nextInt();
				tdao.delete(tid);
				System.out.println("the entry was deleted");
				break;
			case 6:
				System.exit(0);
				break;
			
			}
		}while(true);

	}

}
