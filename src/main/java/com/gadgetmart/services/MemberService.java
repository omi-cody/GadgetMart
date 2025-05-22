package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.MemberModel;

public class MemberService {
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	
	//get the detail of the member
		public ArrayList<MemberModel> getAllMembers(){
			
			try (Connection con = dbconfig.getConnection()) {
	            PreparedStatement st = con.prepareStatement("SELECT * From member");
	            ResultSet rs = st.executeQuery();
	            ArrayList<MemberModel> members = new ArrayList<>();

	            while (rs.next()) {
	            	 MemberModel member = new MemberModel();
	                 member.setMemberFName(rs.getString("Member_FName"));
	                 member.setMemberLName(rs.getString("Member_LName"));
	                 member.setMemberType(rs.getString("Member_Type"));
	                 member.setMemberInfo(rs.getString("Member_Info"));
	                 member.setMemberIntro(rs.getString("Member_Intro"));
	                 member.setMemberImage(rs.getString("Member_Img"));
	                 
	                 members.add(member);
	            }
	            
	            return members;

	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	            return null;
	        }

			
			
			
		}
}
