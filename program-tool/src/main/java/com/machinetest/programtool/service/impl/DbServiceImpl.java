package com.machinetest.programtool.service.impl;

import com.machinetest.programtool.bean.db.MysqlColumn;
import com.machinetest.programtool.bean.db.MysqlTable;
import com.machinetest.programtool.service.IDbService;
import com.machinetest.programtool.service.IMysqlDbService;
import com.machinetest.programtool.service.IOracleDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubinqiang
 */
@Service
public class DbServiceImpl implements IDbService {
    @Autowired
    private IMysqlDbService mysqlDbService;
    @Autowired
    private IOracleDbService oracleDbService;

    @Override
    public void syncDefaultValue() {
        List<MysqlTable> mysqlTableList = mysqlDbService.getTableListByDbName("demo");
        for (MysqlTable mysqlTable : mysqlTableList) {
            oracleDbService.updateTableComment(mysqlTable.getTableName().toUpperCase(), mysqlTable.getTableComment());
            List<MysqlColumn> mysqlColumnList = mysqlDbService.getColumnListByTableName("demo", mysqlTable.getTableName());
            for (MysqlColumn mysqlColumn : mysqlColumnList) {
                oracleDbService.updateColumnComment(mysqlTable.getTableName().toUpperCase(), mysqlColumn.getColumnName().toUpperCase(), mysqlColumn.getColumnComment());
                if (mysqlColumn.getColumnDefault() != null && mysqlColumn.getColumnDefault().length() > 0) {
                    System.out.println("---" + mysqlColumn.getTableName() + ":" + mysqlColumn.getColumnName() + ":" + mysqlColumn.getColumnDefault());
                    oracleDbService.updateColumnDefaultValue(mysqlTable.getTableName().toUpperCase(), mysqlColumn.getColumnName().toUpperCase(), mysqlColumn.getColumnDefault());
                }
            }
        }
    }
}
