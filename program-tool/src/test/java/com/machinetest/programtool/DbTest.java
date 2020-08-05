package com.machinetest.programtool;

import com.machinetest.programtool.service.IDbService;
import com.machinetest.programtool.service.IMysqlDbService;
import com.machinetest.programtool.service.IOracleDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liubinqiang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTest {
    @Autowired
    private IOracleDbService oracleDbService;
    @Autowired
    private IMysqlDbService mysqlDbService;

    @Autowired
    private IDbService dbService;

    @Test
    public void ddd() {
        //oracleDbService.getTableListByOwner("RX");
        //oracleDbService.getColumnListByTableName("RFT_IDENTITY");
        //oracleDbService.updateTableComment("RFT_TAG", "标签1");
        //oracleDbService.updateColumnComment("RFT_TAG", "ID", "IDo ");
        oracleDbService.updateColumnDefaultValue("RFT_TAG", "VERSION", "0");

        //mysqlDbService.getTableListByDbName("rx");
        //mysqlDbService.getColumnListByTableName("rx", "rft_identity");
    }

    @Test
    public void syncDefaultValue() {
        dbService.syncDefaultValue();
    }

}
