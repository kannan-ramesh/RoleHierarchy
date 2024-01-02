package com.kannanrameshrk;

import com.kannanrameshrk.rolehierachy.addrole.AddRoleView;

public class Main {

	public static void main(String[] args) {
		Main main=new Main();
		main.start();
	}

	private void start() {
		System.out.println("\t\tZOHO ROLES");
		System.out.println("\t\t***********");
		AddRoleView addRoleView=new AddRoleView();
		addRoleView.init();
	}

}
