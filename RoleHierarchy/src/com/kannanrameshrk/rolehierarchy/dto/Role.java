package com.kannanrameshrk.rolehierarchy.dto;

public class Role {
	private int id;
	private String roleName;
	private int parentid;
	private String reportName;
	

	public Role(String rootName,int headId) {
		this.roleName=rootName;
		this.parentid=headId;
	}
	

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(String subRoleName, String headName) {
		this.roleName=subRoleName;
		this.reportName=headName;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String rolename) {
		this.roleName = rolename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
