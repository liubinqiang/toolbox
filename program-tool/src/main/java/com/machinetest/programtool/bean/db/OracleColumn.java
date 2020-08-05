package com.machinetest.programtool.bean.db;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liubinqiang
 */
@Data
public class OracleColumn {
    public OracleColumn() {
    }

    public OracleColumn(ResultSet resultSet) {
        try {
            this.tableName = resultSet.getString("table_name");
            this.columnName = resultSet.getString("column_name");
            this.dataType = resultSet.getString("data_type");
            this.dataLength = resultSet.getString("data_length");
            this.dataDefault = resultSet.getString("data_default");
            this.nullable = resultSet.getString("nullable");
            this.comments = resultSet.getString("comments");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String tableName;
    private String columnName;
    private String dataType;
    private String dataLength;
    private String dataDefault;
    private String nullable;
    private String comments;
}
