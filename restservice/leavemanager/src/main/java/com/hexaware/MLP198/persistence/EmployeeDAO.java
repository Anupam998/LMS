package com.hexaware.MLP198.persistence;

import java.util.List;

import com.hexaware.MLP198.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEES")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMP_ID=:empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);
  
  @SqlQuery("SELECT M.* FROM EMPLOYEES E JOIN EMPLOYEES M ON E.EMP_MANAGER_ID = M.EMP_ID WHERE E.EMP_ID = :empID")
  @Mapper(EmployeeManagerMapper.class)
  Employee listManager(@Bind("empID") int empID);
  
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMP_MANAGER_ID = :empManagerId")
  @Mapper(EmployeeMapper.class)
  List<Employee>managerEmployees(@Bind("empManagerId") int empManagerId);
  
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMP_DEPT_NAME= :empDeptName")
  @Mapper(EmployeeMapper.class)
  List<Employee> viewEmpByDeptName(@Bind("empDeptName")String empDeptName);

  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMP_LEAVEBALANCE=(SELECT MAX(EMP_LEAVEBALANCE) FROM EMPLOYEES)")
  @Mapper(EmployeeMapper.class)
  List<Employee>viewMaxLeaveBalance();

  /**
  * close with no args is used to close the connection.
  */
  void close();



}
