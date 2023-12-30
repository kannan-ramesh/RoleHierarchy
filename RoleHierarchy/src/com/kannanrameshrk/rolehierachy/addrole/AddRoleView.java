package com.kannanrameshrk.rolehierachy.addrole;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.kannanrameshrk.rolehierarchy.dto.Role;

public class AddRoleView {
	AddRoleViewModel addRoleViewModel;
	
	public AddRoleView() {
		addRoleViewModel=new AddRoleViewModel(this);
	}

	public void init() {
		boolean loop=true;
		Scanner input = new Scanner(System.in);
		while(loop) {
			System.out.println(" 1.AddRootRole\n 2.AddSubRole\n 3.View Role\n 4.Exit");
			System.out.println("Enter your option:");
			System.out.println("------------------");
			int choice=input.nextInt();
			input.nextLine();
			switch(choice) {
			case 1:{
				System.out.println("Add Roles");
				System.out.println("------------");
				System.out.println("Enter root role name:");
				String rootName=input.next();
				System.out.println("Enter root Id:");
				int headId=input.nextInt();
				Role role=new Role(rootName,headId);
				addRoleViewModel.validate(role);
				break;
			}case 2:{
				System.out.println("\t\tAdd SubRoles");
				System.out.println("\t\t-------------");
				System.out.println("ENter root role name:");
				String rootRoleName=input.nextLine();
				
				boolean flag=true;
				while(flag) {
					System.out.println("+----------------------------------+");
					System.out.println("|     OPERATIONS                   |");
					System.out.println("+----------------------------------+");
					System.out.println("| 1. Add SubRole                   |");
					System.out.println("| 2. Display Role                  |");
					System.out.println("| 3. Delete Role                   |");
					System.out.println("| 4. Add User                      |");
					System.out.println("| 5. Display Role                  |");
					System.out.println("| 6. Display User and Sub-User     |");
					System.out.println("| 7. Delete User                   |");
					System.out.println("| 8. Number of users from top      |");
					System.out.println("| 9. Height of role hierarchy      |");
					System.out.println("|10. Common boss of users          |");
					System.out.println("| 11. Exit...                       |");
					System.out.println("+----------------------------------+");
					System.out.print("--> Operation to be performed : ");
					
					int opt=input.nextInt();
					input.nextLine();
					switch(opt) {
					case 1:{
						System.out.println("Enter Sub Role Name:");
						String subRoleName=input.nextLine();
						System.out.println("Enter Reporting to Role Name:");
						String headName=input.nextLine();
						Role role=new Role(subRoleName,headName);
						addRoleViewModel.validateSubRole(role);
						break;
					}case 2:{
						System.out.println("\t\tDisplay Role");
						System.out.println("\t\t------------");
						java.util.List<Role> list=addRoleViewModel.getRole();
						for(int i=0;i<list.size();i++) {
							System.out.println(list.get(i).getRoleName());
						}
						break;
					}case 3:{
						System.out.println("\t\t Delete Role ");
						System.out.println("\t\t------------");
						break;
					}case 4:{
						System.out.println("\t\t Add User");
						System.out.println("\t\t------------");
						break;
					}case 5:{
						System.out.println("\t\tDisplay Role");
						System.out.println("\t\t------------");
						break;
					}case 6:{
						System.out.println("\t\t Display User and Sub-User ");
						System.out.println("\t\t------------");
						break;
					}case 7:{
						System.out.println("\t\tDelete User");
						System.out.println("\t\t------------");
						break;
					}case 8:{
						System.out.println("\t\tNumber of users from top");
						System.out.println("\t\t------------");
						break;
					}case 9:{
						System.out.println("\t\tHeight of role hierarchy");
						System.out.println("\t\t------------");
						break;
					}case 10:{
						System.out.println("\t\tCommon boss of users");
						System.out.println("\t\t------------");
						break;
					}
					case 11:{
						loop=false;
						AddRoleView addrollview=new AddRoleView();
						addrollview.init();
						break;
					}
					default:{
						System.out.println("Invalid choice...");
						break;
					}
					}
				}
				break;
			}
			case 3:{
				System.out.println("View Roles");
				System.out.println("------------");
				java.util.List<Role> list=addRoleViewModel.getRole();
				for(int i=0;i<list.size();i++) {
					System.out.println(list.get(i).getRoleName());
				}
				break;
			}case 4:{
				System.out.println("Exit your Application");
				loop=false;
				break;
			}default:{
				System.out.println("Invalid choice..");
				break;
			}
			}	
		}
	}

	public void showErr(String errMessage) {
		System.out.println(errMessage);
	}

	public void onSuccess(String onSuccess) {
		System.out.println(onSuccess);
	}

}
