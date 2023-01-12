package com.human.board.dao;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.human.board.domain.Board;
import com.human.board.utils.JDBCUtils;


@Repository // 레포지토리란 무엇인가?? 215페이지에 나옴.
            // 데이터베이스를 처리하는 빈으로 등록.
public class BoardDAO {
	
	// 의존성 자동 주입 - BoardDAO 안에서 JDBCTemplate를 사용하고 싶다.
	
	//@Autowired
	//private JDBCUtils jdbcUtils;
	
	//@Autowired
	//	private JdbcTemplate jdbcTemplate = jdbcUtils.jdbcTemplate();
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	/*private DataSource dataSource;
	
	
	public BoardDAO() {
		jdbcTemplate = new JdbcTemplate(); 
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUsername("human");
		dataSource.setPassword("123456");	
		
		jdbcTemplate.setDataSource(dataSource);
		}
	*/
	
	
	
	// 데이터 목록 조회
	public List<Board> selectList() {
		
		
		String sql = " SELECT * "
				   + " FROM board "
				   + " ORDER BY reg_date DESC";
		
		List<Board> boardList 
		 	= jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
		
		return boardList;
	}
	
	
	
	// 데이터 조회
	public Board select(int board_no) {
		
		
		String sql = " SELECT * "
				   + " FROM board"
				   + " WHERE board_no = ? ";
		
		Board board 
		= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), board_no);
		
		return board;
	}
	
	
	// 데이터 등록
	public int insert(Board board) {
		
		int result = 0;
		
		// oracle
		String sql = " INSERT INTO board ( board_no, title, writer, content ) "
				   + " VALUES( SEQ_BOARD.nextval, ?, ?, ? ) ";
		/* mysql
		String sql = " INSERT INTO board ( title, writer, content ) "
				   + " VALUES( ?, ?, ? ) ";
		*/
		String title = board.getTitle();
		String writer = board.getTitle();
		String content = board.getTitle();
		result = jdbcTemplate.update(sql, title, writer, content);
		
		return result;
	}
	
	
	// 데이터 수정
	public int update(Board board) {
		
		int result = 0;
		String sql = " UPDATE board "
				   + " SET title = ? "
				   + "    ,writer = ? "
				   + "    ,content = ? "
				// + "    ,upd_date = now() "    		//- mysql
				   + "    ,upd_date = sysdate "       	//  - oracle
				   + " WHERE board_no = ? ";
	
		String title = board.getTitle();
		String writer = board.getTitle();
		String content = board.getTitle();
		int boardNo =  board.getBoardNo();
		result = jdbcTemplate.update(sql, title, writer, content, boardNo);
	
		
		return result;
	}
	
	
	// 데이터 삭제
	public int delete(int board_no) {
		
		int result = 0;
		String sql = " DELETE FROM board "
				   + " WHERE board_no = ? ";
		
		result = jdbcTemplate.update(sql, board_no);
		
		
		return result;
	}

}
























