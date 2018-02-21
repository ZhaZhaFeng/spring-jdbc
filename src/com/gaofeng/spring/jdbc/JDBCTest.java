package com.gaofeng.spring.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {
    private ApplicationContext context=null;
    private JdbcTemplate jdbcTemplate;
    {
        context=new ClassPathXmlApplicationContext("spring-config.xml");
        jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
    }
    /*执行批量更新*/
    @Test
    public void testBatchUpdate(){
        String sql="insert into employees(last_name,email,dept_id) values (?,?,?)";
        List<Object[]> batchArgs=new ArrayList<>();
        batchArgs.add(new Object[]{"AA","aa@qq.com",1});
        batchArgs.add(new Object[]{"BB","bb@qq.com",1});
        batchArgs.add(new Object[]{"CC","cc@qq.com",1});
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }
    @Test
    public void testUpdate(){
        String sql="update employees set last_name=? where id=?";
        jdbcTemplate.update(sql,"jack",5);
    }
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource=context.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
    /*从数据库中获取一条记录，实际得到对应的一个对象.
    * 不支持级联属性
    * */
    @Test
    public void testQueryForObject(){
        String sql="select id,last_name lastName,email from employees where id=?";
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
        Employee employee=jdbcTemplate.queryForObject(sql, rowMapper,1);
        System.out.println(employee);
    }
    /*查到实体类的集合*/
    @Test
    public void testQueryForList(){
        String sql="select id,last_name lastName,email from employees where id=?";
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employees=jdbcTemplate.query(sql,rowMapper,5);
        System.out.println(employees);
    }
    /*获取单个列的值，或者做统计查询*/
    @Test
    public void testQueryForObject2(){
        String sql="select count(id) from employees";
        Long count=jdbcTemplate.queryForObject(sql,Long.class);
        System.out.println(count);
    }
}
