
package com.hexaware.MLP198.persistence;

import java.util.Date;
import java.util.List;

import com.hexaware.MLP198.model.LeaveDetails;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface LeaveDetailsDAO {
  /**
   * 
   * 
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEAVE_ID=:leaveId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails find(@Bind("leaveId") int leaveId);

  /**
   * 
   * 
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_STATUS=:status, LEAVE_COMMENT=:comments WHERE LEAVE_ID= :leaveId ")
  void approveOrDenyLeave(@Bind("leaveId") int leaveId, @Bind("status") String status,
      @Bind("comments") String comments);

  @SqlUpdate("INSERT INTO LEAVE_DETAILS (EMP_ID,LEAVE_DAYS,LEAVE_START_DATE,LEAVE_END_DATE,LEAVE_TYPE,LEAVE_REASON)VALUES(:empId,:leaveDays,:leaveStartDate,:leaveEndDate,:leaveType,:leaveReason)")
  void applyEmpLeave(@Bind("empId") int empId, @Bind("leaveDays") int leaveDays,
      @Bind("leaveStartDate") Date leaveStartDate, @Bind("leaveEndDate") Date leaveEndDate,
      @Bind("leaveType") String leaveType, @Bind("leaveReason") String leaveReason);

  
  /**
   * 
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID= :empId")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> leaveHistoryDetails(@Bind("empId") int empId);
  /**
   * 
   */

  @SqlUpdate("UPDATE EMPLOYEES SET EMP_LEAVEBALANCE=EMP_LEAVEBALANCE-(SELECT LEAVE_DAYS FROM leave_details WHERE EMP_ID =:empId ORDER BY LEAVE_APPILED_ON DESC LIMIT 1) WHERE EMP_ID =:empId")
  //@Mapper(LeaveDetailsMapper.class)
  void decrementLeaveOnApproval(@Bind("empId") int empId);

  /**
   * close with no args is used to close the connection.
   */
  void close();


}