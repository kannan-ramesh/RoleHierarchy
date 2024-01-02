package com.kannanrameshrk.rolehierarchy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.kannanrameshrk.rolehierarchy.dto.Role;

public class Repository {
	private static Repository repository=null;
	
	private Repository() {
		
	}
	
	public static Repository getInstance() {
		if(repository==null) {
			repository=new Repository();
		}
		return repository;
	}

	public void insertRole(Role role) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		String query="INSERT INTO  roles (name) VALUES (?)";
		
		con=DataConnection.getConnection();
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,role.getRoleName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Err in database insertion process..");
		}finally {
			DataConnection.closeConnection();
		}
	}
	public boolean insertSubRole(Role role) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=DataConnection.getConnection();
		int parentID=getParentId(role,con);
		
		if(parentID==0) {
			return false;
		}else {
			String query="INSERT INTO roles(name,headID) VALUES (?,?)";
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1,role.getRoleName());
				pstmt.setInt(2, parentID);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private int getParentId(Role role, Connection con) {
		PreparedStatement pstmt=null;
		String headName=role.getReportName();
		String query ="select roleID from roles where name=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, headName);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("roleID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Role> getRole() {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Role> roleList=new LinkedList<>();
		
		String query = "select * from roles ORDER BY headID";
		
		con=DataConnection.getConnection();
		try {
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Role role=new Role();
				role.setId(rs.getInt("roleID"));
				role.setRoleName(rs.getString("name"));
				role.setParentid(rs.getInt("headID"));
				roleList.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}
	public boolean removeSubRole(String removeRole, String transferRole) {
		Role roleRemove=getRoleByName(removeRole);
		Role transRole=getRoleByName(transferRole);
		
		if(roleRemove != null && transRole != null) {
			List<Role> swap=traverseSwap(roleRemove.getId());
			
			for(Role data:swap) {
				data.setParentid(transRole.getId());
				updateRoleParentId(data);
			}
			deleteRole(roleRemove.getId());
		}
		return false;
	}

	private void deleteRole(int id) {
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            con = DataConnection.getConnection();
	            String query = "DELETE FROM roles WHERE roleID = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DataConnection.closeConnection();
	        }
	}

	private void updateRoleParentId(Role data) {
	    Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataConnection.getConnection();
            String query = "UPDATE roles SET headID = ? WHERE roleID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, data.getParentid());
            pstmt.setInt(2, data.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataConnection.closeConnection();
        }
	}

	private List<Role> traverseSwap(int id) {
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<Role> swapList = new ArrayList<>();

	        try {
	            con = DataConnection.getConnection();
	            String query = "SELECT * FROM roles WHERE headID = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, id);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                Role role = new Role();
	                role.setId(rs.getInt("roleID"));
	                role.setRoleName(rs.getString("name"));
	                role.setParentid(rs.getInt("headID"));
	                swapList.add(role);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DataConnection.closeConnection();
	        }

	        return swapList;
	}

	private Role getRoleByName(String roleName) {
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Role role = null;

	        try {
	            conn =DataConnection.getConnection();
	            String query = "SELECT * FROM roles WHERE name = ?";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, roleName);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                role = new Role();
	                role.setId(rs.getInt("roleID"));
	                role.setRoleName(rs.getString("name"));
	                role.setParentid(rs.getInt("headID"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	           DataConnection.closeConnection();
	        }
	        return role;
	}

}
