package com.machinetest.programtool.service;

import com.machinetest.programtool.bean.db.MysqlColumn;
import com.machinetest.programtool.bean.db.MysqlTable;
import com.machinetest.programtool.bean.db.OracleColumn;
import com.machinetest.programtool.bean.db.OracleTable;

import java.util.List;

/**
 * @author liubinqiang
 */
public interface IMysqlDbService {

    List<MysqlTable> getTableListByDbName(String dbName);

    List<MysqlColumn> getColumnListByTableName(String dbName, String tableName);
}
