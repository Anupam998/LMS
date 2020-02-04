package com.hexaware.MLP198.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.MLP198.model.LeaveDetails;
import com.hexaware.MLP198.model.LeaveStatus;
import com.hexaware.MLP198.model.LeaveType;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {

  public LeaveDetails map(int index, final ResultSet r, StatementContext ctx) throws SQLException {

    return new LeaveDetails(r.getInt("LEAVE_ID"), r.getDate("LEAVE_START_DATE"), r.getDate("LEAVE_END_DATE"),
        r.getInt("LEAVE_DAYS"), LeaveType.valueOf(r.getString("LEAVE_TYPE")),
        LeaveStatus.valueOf(r.getString("LEAVE_STATUS")), r.getTimestamp("LEAVE_APPILED_ON"),
        r.getString("LEAVE_REASON"), r.getString("LEAVE_COMMENT"), r.getInt("EMP_ID"));
  }

}