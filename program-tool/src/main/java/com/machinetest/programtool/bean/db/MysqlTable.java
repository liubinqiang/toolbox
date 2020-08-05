package com.machinetest.programtool.bean.db;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liubinqiang
 */
@Data
public class MysqlTable {
    public MysqlTable() {
    }

    public MysqlTable(ResultSet resultSet) {
        try {
            this.tableSchema = resultSet.getString("table_schema");
            this.tableName = resultSet.getString("table_name");
            this.tableType = resultSet.getString("table_type");
            this.tableComment = resultSet.getString("table_comment");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String tableSchema;
    private String tableName;
    private String tableType;
    private String tableComment;
}
