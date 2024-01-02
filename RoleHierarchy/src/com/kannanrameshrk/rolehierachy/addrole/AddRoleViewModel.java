package com.kannanrameshrk.rolehierachy.addrole;

import java.util.List;
import java.util.Queue;

import com.kannanrameshrk.rolehierarchy.dto.Role;
import com.kannanrameshrk.rolehierarchy.repository.Repository;

class AddRoleViewModel {
	AddRoleView addRoleView;
	
	public AddRoleViewModel(AddRoleView addRoleView) {
		this.addRoleView=addRoleView;
	}

	public void validate(Role role) {
		if(role.getRoleName().length()<70 && role.getRoleName().matches("[a-zA-Z\\s]+")) {
			Repository.getInstance().insertRole(role);
		}else {
			addRoleView.showErr("Min Character 70 letter ,Not enter Numbers..");
		}
	}

	public List<Role> getRole() {
		return Repository.getInstance().getRole();
	}

	public void validateSubRole(Role role) {
		boolean roleName=nameValid(role.getRoleName());
		boolean rootName=nameValid(role.getReportName());
		
		if(roleName && rootName) {
			boolean subReg=Repository.getInstance().insertSubRole(role);
			if(subReg) {
				addRoleView.onSuccess("Successfully Registerd..");
			}else {
				addRoleView.showErr("Not in Root User please add Root User");
			}
		}else {
			addRoleView.showErr("Min Character 70 letter ,Not enter Numbers..");
		}
	}

	private boolean nameValid(String roleName) {
		if(roleName.length()<70 && roleName.matches("[a-zA-Z\\s]+")) {
			return true;
		}
		return false;
	}

	public void validateRemoveRole(Role role) {
		boolean removeRole=nameValid(role.getRoleName());
		boolean transferRole=nameValid(role.getReportName());
		String removeRoleName=role.getRoleName();
		String transferRoleName=role.getReportName();
		
		if(removeRole && transferRole) {
			boolean remRol=Repository.getInstance().removeSubRole(removeRoleName,transferRoleName);
			if(remRol) {
				
			}else {
				
			}
		}
	}
}
