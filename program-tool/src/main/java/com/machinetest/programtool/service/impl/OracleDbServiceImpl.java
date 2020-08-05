package com.machinetest.programtool.service.impl;

import com.machinetest.programtool.bean.db.OracleColumn;
import com.machinetest.programtool.bean.db.OracleTable;
import com.machinetest.programtool.service.IOracleDbService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liubinqiang
 */
@Slf4j
@Service
public class OracleDbServiceImpl implements IOracleDbService {

    private static final String DB_URL = "jdbc:oracle:thin:@192.168.1.5:1521:orcl";
    private static final String DB_USER = "demo";
    private static final String DB_PWD = "demo";

    private <T> List<T> queryDb(Class<T> tClass, String sql) {
        List<T> list = new ArrayList<>();
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PWD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(DB_URL);
            ods.setConnectionProperties(info);
            try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
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

    private boolean execute(String sql) {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PWD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(DB_URL);
            ods.setConnectionProperties(info);
            try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
                try (Statement statement = connection.createStatement()) {
                    int exeRet = statement.executeUpdate(sql);
                    return exeRet > 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(sql);
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<OracleTable> getTableListByOwner(String owner) {
        String sql = "select * from all_tab_comments where OWNER='" + owner + "' and table_type='TABLE'";
        List<OracleTable> list = queryDb(OracleTable.class, sql);
        return list;
    }

    @Override
    public List<OracleColumn> getColumnListByTableName(String tableName) {
        String sql = "select col.table_name,col.column_name,data_type,data_length,data_default,nullable ,c.comments " +
                "from user_tab_cols col left join user_col_comments c on col.column_name=c.column_name " +
                "where col.table_name='" + tableName + "' and c.table_name='" + tableName + "'";
        List<OracleColumn> list = queryDb(OracleColumn.class, sql);
        return list;
    }

    @Override
    public boolean updateTableComment(String tableName, String comment) {
        String sql = "comment on table " + tableName + " is '" + comment + "'";
        boolean ret = execute(sql);
        System.out.println(sql);
        return ret;
    }

    @Override
    public boolean updateColumnComment(String tableName, String column, String comment) {
        String sql = "comment on column " + tableName + "." + column + " is '" + comment + "'";
        boolean ret = execute(sql);
        System.out.println(sql);
        return ret;
    }

    @Override
    public boolean updateColumnDefaultValue(String tableName, String column, String defaultValue) {

        if (!isNumber(defaultValue) && !defaultValue.contains("CURRENT_TIMESTAMP") && !defaultValue.equals("b'0'")) {
            defaultValue = "'" + defaultValue + "'";
        }
        if (defaultValue.equals("b'0'")) {
            defaultValue = "0";
        }
        String sql = "alter table " + tableName + " modify " + column + " default " + defaultValue;
        boolean ret = execute(sql);
        System.out.println(sql);
        return ret;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
