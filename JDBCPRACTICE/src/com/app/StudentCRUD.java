package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class StudentCRUD {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/dbname";
		String un="root";
		String pwd="your_password";
		return DriverManager.getConnection(url, un, pwd);
	}
	
	public void insertStudent(Scanner sc){
		try(Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?)");
		) {
			System.out.println("Do you want to insert record(yes/no)");
			String choice = sc.nextLine();
			
			while(choice.equalsIgnoreCase("yes")) {
				System.out.println("Enter student id");
				int id = sc.nextInt();
				sc.nextLine();
				stmt.setInt(1,id);
				System.out.println("Enter Student name");
				String name = sc.nextLine();
				stmt.setString(2,name);
				System.out.println("Enter Student Course");
				String course = sc.nextLine();
				stmt.setString(3,course);
				stmt.executeUpdate();
				System.out.println("Do you want to insert one more record(yes/no)");
				choice=sc.nextLine();
			}
			System.out.println("thank you records are inserted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateStudent(Scanner sc) {
		try(Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement("update student set course=? where sid=?");
			) {
			System.out.println("Enter the student id you want update");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the updated Course name");
			String course = sc.nextLine();
			stmt.setString(1, course);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if(count>0) {
				System.out.println("Student Course is Updated");
			}else {
				System.out.println("Student Not Found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteStudent(Scanner sc) {
		try(Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from student where sid=?");
			) {
			System.out.println("Enter the Student id you want to delete");
			int id = sc.nextInt();
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if(count>0) {
				System.out.println("Student was deleted");
			}else {
				System.out.println("Student Not Found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void displayStudent() {
		try(Connection con = getConnection();
				Statement stmt=con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from student");)
		{
			
			ResultSetMetaData meta = rs.getMetaData();
			System.out.println(meta.getColumnName(1)+" "+meta.getColumnName(2)+"  "+meta.getColumnName(3));
			boolean hasRecords=false;
			
			while(rs.next()) {
				hasRecords=true;
				System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3));
			}
			if(!hasRecords) {
				System.out.println("no record found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findByStudentId(Scanner sc) {
		try(Connection con = getConnection();
				PreparedStatement stmt=con.prepareStatement("select * from student where sid=?");
				
		) {
			System.out.println("Enter the student id you want to search");
		    int id = sc.nextInt();
		    stmt.setInt(1, id);
		    ResultSet rs=stmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			System.out.println(meta.getColumnName(1)+" "+meta.getColumnName(2)+"  "+meta.getColumnName(3));
			if(rs.next()) {
				System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3));
			}else {
				System.out.println("no record found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static  int menu(Scanner sc) {
		System.out.println();
		System.out.println("Choose One Option");
		System.out.println("1.insertrecord");
		System.out.println("2.updaterecord");
		System.out.println("3.deleterecord");
		System.out.println("4.displayrecord");
		System.out.println("5.findrecord");
		System.out.println("6.exit");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
		
	}
	public static void main(String[] args) {
		StudentCRUD s = new StudentCRUD();
		Scanner sc = new Scanner(System.in);
		System.out.println("*********welcome to student management system********");
		while(true) {
			  int choice = menu(sc);
			switch(choice) {
			case 1:
				s.insertStudent(sc);
				break;
			case 2:
				s.updateStudent(sc);
				break;
			case 3:
				s.deleteStudent(sc);
				break;
			case 4:
				s.displayStudent();
				break;
			case 5:
				s.findByStudentId(sc);
				break;
			case 6:
				sc.close();
				System.exit(0);
			default:
				System.out.println("invalid option");
			}

		}
		
	}

}
