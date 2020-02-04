package com.hexaware.MLP198.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * LeaveDetails
 */

import java.util.Date;
import java.util.List;

import com.hexaware.MLP198.persistence.DbConnection;
import com.hexaware.MLP198.persistence.LeaveDetailsDAO;

public class LeaveDetails {

  private int leaveId;
  private Date leaveStartDate;
  private Date leaveEndDate;
  private int leaveDays;
  private LeaveType leaveType;
  private LeaveStatus leaveStatus;
  private Date leaveAppliedOn;
  private String leaveReason;
  private String leaveComment;
  private int empId;

  public LeaveDetails() {
  }

  public LeaveDetails(int leaveId, Date leaveStartDate, Date leaveEndDate, int leaveDays, LeaveType leaveType,
      LeaveStatus leaveStatus, Date leaveAppliedOn, String leaveReason, String leaveComment, int empId) {
    this.leaveId = leaveId;
    this.leaveStartDate = leaveStartDate;
    this.leaveEndDate = leaveEndDate;
    this.leaveDays = leaveDays;
    this.leaveType = leaveType;
    this.leaveStatus = leaveStatus;
    this.leaveAppliedOn = leaveAppliedOn;
    this.leaveReason = leaveReason;
    this.leaveComment = leaveComment;
    this.empId = empId;
  }

  public int getLeaveId() {
    return leaveId;
  }

  public void setLeaveId(int leaveId) {
    this.leaveId = leaveId;
  }

  public Date getLeaveStartDate() {
    return leaveStartDate;
  }

  public void setLeaveStartDate(Date leaveStartDate) {
    this.leaveStartDate = leaveStartDate;
  }

  public Date getLeaveEndDate() {
    return leaveEndDate;
  }

  public void setLeaveEndDate(Date leaveEndDate) {
    this.leaveEndDate = leaveEndDate;
  }

  public int getDays() {
    return leaveDays;
  }

  public void setDays(int leaveDays) {
    this.leaveDays = leaveDays;
  }

  public LeaveType getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(LeaveType leaveType) {
    this.leaveType = leaveType;
  }

  public LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }

  public void setLeaveStatus(LeaveStatus leaveStatus) {
    this.leaveStatus = leaveStatus;
  }

  public Date getLeaveAppliedOn() {
    return leaveAppliedOn;
  }

  public void setLeaveAppliedOn(Date leaveAppliedOn) {
    this.leaveAppliedOn = leaveAppliedOn;
  }

  public String getLeaveReason() {
    return leaveReason;
  }

  public void setLeaveReason(String leaveReason) {
    this.leaveReason = leaveReason;
  }

  public String getLeaveComment() {
    return leaveComment;
  }

  public void setLeaveComment(String leaveComment) {
    this.leaveComment = leaveComment;
  }

  @Override
  public String toString() {
    return "LeaveDetails [leaveDays=" + leaveDays + ", leaveAppliedOn=" + leaveAppliedOn + ", leaveComment="
        + leaveComment + ", leaveEndDate=" + leaveEndDate + ", leaveId=" + leaveId + ", leaveReason=" + leaveReason
        + ", leaveStartDate=" + leaveStartDate + ", leaveStatus=" + leaveStatus + ", leaveType=" + leaveType + "]";
  }

  /**
   * The dao for leaveDetail.
   */
  private static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }

  public static LeaveDetails listById(int leaveId) {
    return dao().find(leaveId);
  }

  public static void approveLeaveById(int leaveId, String status, String comments) {
    dao().approveOrDenyLeave(leaveId, status, comments);
  }

  public static void applyEmpLeave(int empId, int leaveDays, String leaveStartDate, String leaveEndDate,
      String leaveType, String leaveReason) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = null;
    Date endDate = null;
    try {
      startDate = sdf.parse(leaveStartDate);
    } catch (ParseException e) {
      System.out.println("Enter valid Date format");
      e.printStackTrace();
    }
    try {
      endDate = sdf.parse(leaveEndDate);
    } catch (ParseException e) {
      System.out.println("Enter valid Date format");
      e.printStackTrace();
    }
    dao().applyEmpLeave(empId, leaveDays, startDate, endDate, leaveType, leaveReason);
  }

  public static LeaveDetails[] leavePreview(int empId) {
    List<LeaveDetails> list = dao().leaveHistoryDetails(empId);
    return list.toArray(new LeaveDetails[list.size()]);
  }

public static void decrementLeave(int empId) {
     dao().decrementLeaveOnApproval(empId);

}

}