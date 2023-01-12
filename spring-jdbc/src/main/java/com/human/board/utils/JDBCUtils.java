package com.human.board.utils;

import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JDBCUtils {

	// @Autowired
	// DataSource dataSource;
	
//	private DriverManagerDataSource dataSource;
//	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		
//		
//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
//		dataSource.setUsername("human");
//		dataSource.setPassword("123456");
//		
//		log.info("########## dataSource ##########");
//		log.info(dataSource.toString());
//		
//		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		jdbcTemplate.setDataSource( dataSource );
//		jdbcTemplate.afterPropertiesSet( );
//		return jdbcTemplate;
//		
//	}
	
}
