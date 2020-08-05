package com.machinetest.programtool.bean.db;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liubinqiang
 */
@Data
public class OracleTable {
    public OracleTable() {
    }

    public OracleTable(ResultSet resultSet) {
        try {
            this.owner = resultSet.getString("owner");
            this.tableName = resultSet.getString("table_name");
            this.tableType = resultSet.getString("table_type");
            this.comments = resultSet.getString("comments");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String owner;
    private String tableName;
    private String tableType;
    private String comments;
}
