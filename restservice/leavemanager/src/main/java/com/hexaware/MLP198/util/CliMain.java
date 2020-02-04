package com.hexaware.MLP198.util;

import java.util.Scanner;

import com.hexaware.MLP198.model.Employee;
import com.hexaware.MLP198.model.LeaveDetails;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. View Manager Details");
    System.out.println("4. Apply for Leave");
    System.out.println("5. Approve Or Deny");
    System.out.println("6. View All Employees Under Manager");
    System.out.println("7. View Leave Details");
    System.out.println("8. View Leave History");
    System.out.println("9. View Employee department name");
    System.out.println("10. View Employee Name having Maximum Leave Balance");
    System.out.println("11. Exit");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }

  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
    case 1:
      listEmployeesDetails();
      break;
    case 2:
      listEmployeeDetail();
      break;
    case 3:
    listManagerDetails();
      break;
    case 4:
      applyLeave();
      break;
    case 5:
     approveorDenyLeave();
      break;
    case 6:
        listManagerEmployeesDetails();
        break;
    case 7:
       viewLeaveById();
        break;
    case 8: 
       leaveHistory();  
       break;
    case 9:viewEmpByDeptName();
    break;
    case 10:viewMaxLeaveBalance();
    break;
    case 11:  
      // halt since normal exit throws a stacktrace due to jdbc threads not responding
      Runtime.getRuntime().halt(0);

    default:
      System.out.println("Choose either 1, 2 or 3");
    }
    mainMenu();
  }

  int empId=0;
  private void viewMaxLeaveBalance(){
  Employee[] ee= Employee.viewMaxLeaveBalance();
  for(Employee m: ee)
  {
    System.out.println(m.getEmpName());
  }
}
  private void viewEmpByDeptName(){
    System.out.println("Enter dept name");
    String empDeptName = option.next();
    Employee[] employee = Employee.viewEmpByDeptName(empDeptName);

    if(employee.length >0){
    for(Employee e : employee) {
      System.out.println(e.getEmpName());
    }
  }
  else 
  System.out.println("NO Employee found in " + empDeptName);
  }
   
  private void applyLeave() {
    int empId;
    int leaveDays;
    String leaveStartDate;
    String leaveEndDate;
    String leaveType;
    String leaveReason;
    System.out.println("Enter the employyeId");
    empId = option.nextInt();
    System.out.println("Enter the number of days");
    leaveDays = option.nextInt();
    System.out.println("Enter the leave StartDate");
    leaveStartDate = option.next();
    System.out.println("Enter the leave EndDate");
    leaveEndDate = option.next();
    System.out.println("Enter the type of the leave");
    System.out.println(" 1 Earned Leave");
    System.out.println("2 Meternity Leave");
    System.out.println("3 Peternity Leave");
    int leaveChoice = option.nextInt();
    leaveType = " ";
    switch (leaveChoice) {
    case 1:
      leaveType = "Earned_Leave";
      break;
    case 2:
      leaveType = "Maternity_Leave";
      break;
    case 3:
      leaveType = "Paternity_Leave";
      break;
    default:
      System.out.println("Invalid Choice");
      break;
    }
    option.nextLine();
    System.out.println("Enter the reason for leave");
    leaveReason = option.nextLine();
    LeaveDetails.applyEmpLeave(empId, leaveDays, leaveStartDate, leaveEndDate, leaveType, leaveReason);

    System.out.println("leave applied successfully");
  }

  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee.getEmpId());
      System.out.println(employee.getEmpName());
      System.out.println(employee.getEmpEmail());
      System.out.println(employee.getEmpDeptName());
      System.out.println(employee.getEmpPhoneNumber());
      System.out.println(employee.getEmpDateOfJoining());
      System.out.println(employee.getEmpLeaveBalance());
      System.out.println(employee.getEmpManagerId());
    }
    System.exit(0);
  }

  private void viewLeaveById() {
    System.out.println("Enter the leave ID");
    int leaveId = option.nextInt();
    LeaveDetails leaveDetails = LeaveDetails.listById(leaveId);
    System.out.println(leaveDetails);
  }

  private void approveorDenyLeave() {
    System.out.println("Enter the leave ID");
    int leaveId = option.nextInt();
    System.out.println("Enter the Employee Id");
    empId=option.nextInt();
    System.out.println("Enter (1) to approve and (2) to Deny");
    int choice= option.nextInt();
    String status="";
    switch(choice){
      case 1:
      status="Approved";
      LeaveDetails.decrementLeave(empId);
      break;
    case 2:
      status = "Denied";
      break;
    }
    System.out.println("Enter Comments");
    String comments = option.next();
    LeaveDetails.approveLeaveById(leaveId, status, comments);
  }

  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.getEmpId());
      System.out.println(e.getEmpName());
      System.out.println(e.getEmpEmail());
      System.out.println(e.getEmpDeptName());
      System.out.println(e.getEmpPhoneNumber());
      System.out.println(e.getEmpDateOfJoining());
      System.out.println(e.getEmpLeaveBalance());
      System.out.println(e.getEmpManagerId());
      System.out.println(e.getEmpLeaveBalance());
    }
  }

  private void leaveHistory() {
    System.out.println("Enter the employee id");
    int empId = option.nextInt();
    // LeaveDetails[] LeaveDetails = LeaveDetails.listById(empId);
    LeaveDetails[] ld = LeaveDetails.leavePreview(empId);
    for (LeaveDetails ll : ld) {
      System.out.println("The leave Id is " + ll.getLeaveId() + "\n" + "The comment is " + ll.getLeaveComment() + "\n"
          + "The reason is " + ll.getLeaveReason() + "\n" + "The leave start date is " + ll.getLeaveStartDate() + "\n"
          + "The leave end date is " + ll.getLeaveEndDate() + "\n" + "The leave applied date is "
          + ll.getLeaveAppliedOn() + "\n" + "The leave type is " + ll.getLeaveType() + "\n" + "The leave status is "
          + ll.getLeaveStatus() + "\n" + "The employee id is " + empId);
    }

  }

  private void listManagerDetails() {
    System.out.println("Enter the Employee id");
    int empId = option.nextInt();
    Employee manager = Employee.findManager(empId);
    if (manager == null) {
      System.out.println("sorry,No such employee");
      System.exit(0);
    } else {
      // Employee manager = Employee.listById(employee.getEmpManagerId());
      System.out.println("Manager Id is " + manager.getEmpId());
      System.out.println("Manager Name is " + manager.getEmpName());
    }
  }
  private void listManagerEmployeesDetails() {
    System.out.println("Enter the Manager Id ");
    int empManagerId = option.nextInt();
    Employee[] employee = Employee.listByManagerId(empManagerId);
    // Object ME;
	for(Employee MGR: employee)
    {
      System.out.println("employee Id is " + MGR.getEmpId() + " " + "Employee name is " + MGR.getEmpName() + " " + "Employee email is " + MGR.getEmpEmail() + " " + "Employee Department name is " + MGR.getEmpDeptName() + " " + "Employee Phone number is " + MGR.getEmpPhoneNumber() + " " + "Employee Date Of joining is " + MGR.getEmpDateOfJoining() +" " + "Employee leave balance is " + MGR.getEmpLeaveBalance() +" " + "Employee Manager Id is " + MGR.getEmpManagerId() + " ");


    }
    System.out.println("Employee Details" + employee);

    }
   

  // private void applyLeave() {
  // System.out.print("Enter the number of days");
  // int numberOfDays = option.nextInt();
  // System.out.println("The number of days is " + numberOfDays);
  // System.out.println("Enter the leave start date");
  // String ssdate = option.next();
  // Date sdate = new SimpleDateFormat("dd/MM/yyyy").parse(ssdate);
  // System.out.println("Leave start date is " + sdate);
  // System.out.print("Enter the leave end date");
  // String sedate = option.next();
  // Date edate = new SimpleDateFormat("dd/MM/yyyy").parse(sedate);
  // System.out.print("The leave end date is " + edate);
  // System.out.print("Enter the type of leave");
  // System.out.print("1)Earn Leave 2)Sick Leave 3)Optional Leave");
  // int choice = option.nextInt();
  // switch (choice) {
  // case 1:
  // System.out.print("The leave is a Earn Leave");
  // break;
  // case 2:
  // System.out.print("The leave is a Sick Leave");
  // break;
  // case 3:
  // System.out.print("The leave is a Optional Leave");
  // }
  // }

  /**
   * The main entry point.
   * 
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
