package com.human.service;
// 인터페이스임
import java.util.List;

import com.human.domain.Board;
import com.human.domain.Page;

public interface BoardService {

	// 게시글 목록을 요청하는 메소드를 만들자

	// 게시글 목록
	public List<Board> list() throws Exception;
	
	// 게시글 조회  
	public Board read(int boardNo) throws Exception;
	
	// 게시글 등록
	public int insert(Board board) throws Exception;
	
	// 게시글 수정
	public int update(Board board) throws Exception;
	
	// 게시글 삭제
	public int delete(int boardNo) throws Exception;

	// 게시글 검색 (매개변수가 키워드인)
	public List<Board> list(String keyword) throws Exception;

	public List<Board> list(Page page) throws Exception;

	// read 부터 delete 까지 오버라이딩 ㄱㄱ
}

// 추상메소드가 뭐였더라 ㅅㅂ거 