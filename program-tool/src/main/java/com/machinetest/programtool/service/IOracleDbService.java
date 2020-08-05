package com.machinetest.programtool.service;

import com.machinetest.programtool.bean.db.OracleColumn;
import com.machinetest.programtool.bean.db.OracleTable;

import java.util.List;

/**
 * @author liubinqiang
 */
public interface IOracleDbService {
    List<OracleTable> getTableListByOwner(String owner);

    List<OracleColumn> getColumnListByTableName(String tableName);

    boolean updateTableComment(String tableName, String comment);

    boolean updateColumnComment(String tableName, String column, String comment);

    boolean updateColumnDefaultValue(String tableName, String column, String defaultValue);
}
