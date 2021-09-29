package com.machinetest.programtool.service.impl;

import com.machinetest.programtool.bean.db.MysqlColumn;
import com.machinetest.programtool.bean.db.MysqlTable;
import com.machinetest.programtool.service.IMysqlDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liubinqiang
 */
@Slf4j
@Service
public class MysqlDbServiceImpl implements IMysqlDbService {

    private static final String DB_URL = "jdbc:mysql://192.168.0.1:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&user=root&password=demo";

    private <T> List<T> queryDb(Class<T> tClass, String sql) {
        List<T> list = new ArrayList<>();
        try {
            try (Connection connection = DriverManager.getConnection(DB_URL);) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery(sql)) {
                        while (resultSet.next()) {
                            T t = tClass.getConstructor(ResultSet.class).newInstance(resultSet);
                            list.add(t);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("", ex);
        } catch (Exception ex) {
            log.error("", ex);
        }
        return list;
    }

    @Override
    public List<MysqlTable> getTableListByDbName(String dbName) {
        String sql = "select table_schema,table_name,table_type,table_comment from information_schema.tables where table_schema='" + dbName + "' and table_type='BASE TABLE'";
        List<MysqlTable> list = queryDb(MysqlTable.class, sql);
        return list;
    }

    @Override
    public List<MysqlColumn> getColumnListByTableName(String dbName, String tableName) {
        String sql = "select table_schema,table_name,column_name,column_default,is_nullable,data_type,column_type,column_comment from information_schema.COLUMNS " +
                "where table_name ='" + tableName + "' and table_schema='" + dbName + "'";
        List<MysqlColumn> list = queryDb(MysqlColumn.class, sql);
        return list;
    }
}
