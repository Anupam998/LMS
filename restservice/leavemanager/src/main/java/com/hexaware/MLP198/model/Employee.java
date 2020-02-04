package com.hexaware.MLP198.model;

import com.hexaware.MLP198.persistence.DbConnection;
import com.hexaware.MLP198.persistence.EmployeeDAO;

import java.util.Objects;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * 
 * @author hexware
 */
public class Employee {

  private static List<Employee> empList;
  /**
   * empId to store employee id.
   */
  private int empId;
  private String empName;
  private String empEmail;
  private String empDeptName;
  private Long empPhoneNumber;
  private Date empDateOfJoining;
  private int empLeaveBalance;
  private int empManagerId;

  public Employee(int i, int j, String string) {
  }

  public Employee(int empId, String empName, String empEmail, String empDeptName, Long empPhoneNumber,
      Date empDateOfJoining, int empLeaveBalance, int empManagerId) {
    this.empId = empId;
    this.empName = empName;
    this.empEmail = empEmail;
    this.empDeptName = empDeptName;
    this.empPhoneNumber = empPhoneNumber;
    this.empDateOfJoining = empDateOfJoining;
    this.empLeaveBalance = empLeaveBalance;
    this.empManagerId = empManagerId;
  }
  public Employee(int empId, int empManagerId) {
    this.empId = empId;
    this.empManagerId = empManagerId;
  }


  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId);
  }

  /**
   * @param argEmpId to initialize employee id.
   */
  public Employee(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * Gets the EmployeeId.
   * 
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * 
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    System.out.println(es.size());
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * 
   * @param empID id to get employee details.
   * @return Employee
   * 
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEmpEmail() {
    return empEmail;
  }

  public void setEmpEmail(String empEmail) {
    this.empEmail = empEmail;
  }

  public String getEmpDeptName() {
    return empDeptName;
  }

  public void setEmpDeptName(String empDeptName) {
    this.empDeptName = empDeptName;
  }

  public Long getEmpPhoneNumber() {
    return empPhoneNumber;
  }

  public void setEmpPhoneNumber(Long empPhoneNumber) {
    this.empPhoneNumber = empPhoneNumber;
  }

  public Date getEmpDateOfJoining() {
    return empDateOfJoining;
  }

  public void setEmpDateOfJoining(Date empDateOfJoining) {
    this.empDateOfJoining = empDateOfJoining;
  }

  public int getEmpLeaveBalance() {
    return empLeaveBalance;
  }

  public void setEmpLeaveBalance(int empLeaveBalance) {
    this.empLeaveBalance = empLeaveBalance;
  }

  public int getEmpManagerId() {
    return empManagerId;
  }

  public void setEmpManagerId(int empManagerId) {
    this.empManagerId = empManagerId;
  }

  @Override
  public String toString() {
    return "Employee [empDateOfJoining=" + empDateOfJoining + ", empDeptName=" + empDeptName + ", empEmail=" + empEmail
        + ", empId=" + empId + ", empLeaveBalance=" + empLeaveBalance + ", empManagerId=" + empManagerId + ", empName="
        + empName + ", empPhoneNumber=" + empPhoneNumber + "]";
  }
  public static Employee[] listByManagerId(final int empManagerId) {
    List <Employee> list=dao().managerEmployees(empManagerId);
    return list.toArray(new Employee[list.size()]);
  }
  public static Employee findManager(final int empId) {
    return dao().listManager(empId);
  }
  public static Employee[] viewEmpByDeptName(String empDeptName){
    List<Employee> empList = dao().viewEmpByDeptName(empDeptName);
    return empList.toArray(new Employee[empList.size()]);
  }
  
  

public static Employee[] viewMaxLeaveBalance(){
   List<Employee> empList = dao().viewMaxLeaveBalance();
	return empList.toArray(new Employee[empList.size()]);
}
}
  

