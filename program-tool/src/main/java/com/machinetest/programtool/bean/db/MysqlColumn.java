package com.machinetest.programtool.bean.db;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liubinqiang
 */
@Data
public class MysqlColumn {
    public MysqlColumn() {
    }

    public MysqlColumn(ResultSet resultSet) {
        try {
            this.tableSchema = resultSet.getString("table_schema");
            this.tableName = resultSet.getString("table_name");
            this.columnName = resultSet.getString("column_name");
            this.columnDefault = resultSet.getString("column_default");
            this.isNullable = resultSet.getString("is_nullable");
            this.dataType = resultSet.getString("data_type");
            this.columnType = resultSet.getString("column_type");
            this.columnComment = resultSet.getString("column_comment");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String tableSchema;
    private String tableName;
    private String columnName;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String columnType;
    private String columnComment;
}
