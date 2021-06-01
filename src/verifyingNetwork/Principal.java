/**
 * 
 */
package verifyingNetwork;

import java.util.Scanner;

/**
 * @author Axel Ignacio Beltran Alvarez
 * 30/05/2021
 * License: GNU GENERAL PUBLIC LICENSE
 * Version: 0.1
 * 
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		conSQL con = new conSQL();
		Scanner sc = new Scanner(System.in);
		String userId, pass;
		
		System.out.print("UserId: ");
		userId = sc.nextLine();
		System.out.print("Password: ");
		pass = sc.nextLine();
		if(con.validateUser(userId, pass)) {
			System.out.println("User Ok");
			con.getIp();
		}
		else {
			System.out.println("User error, validate de userId/Password");
		}
	}

}
