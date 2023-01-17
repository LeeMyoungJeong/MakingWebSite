package com.human.service;

import java.io.File;
import java.io.FileOutputStream;
//import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.human.domain.Board;
import com.human.domain.Files;
import com.human.domain.Page;
import com.human.mapper.BoardMapper;
import com.human.mapper.FileMapper;
import com.human.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service			// 컨테이너의 빈으로 등록, 비즈니스 로직을 처리하는 서비스로 구분
public class BoardServiceImpl implements BoardService {
//The type BoardServiceImpl must implement the inherited abstract method BoardService.read(int)
// 추상메소드를 반드시 정의해야 한다. 	
	
	// 업로드 경로
	@Value("${upload.path}")			//application.properties의 속성값을 지정
	private String uploadPath;
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public List<Board> list() throws Exception {
		
		return mapper.list();
	}

	@Override
	public Board read(int boardNo) throws Exception {
		return mapper.read(boardNo);
	}

	@Override
	public int insert(Board board) throws Exception {
		// 게시글 등록
		 int result = mapper.insert(board);
		 
		 if( result == 0) return result;    	// 게시글 등록이 안 되면, 파일도 업로드 X
		
		
		return result;
	}

	@Override
	public int update(Board board) throws Exception {
		return mapper.update(board);
	}

	@Override
	public int delete(int boardNo) throws Exception {
	
		return mapper.delete(boardNo);
	}

	@Override
	public List<Board> list(String keyword) throws Exception {
		// 아까 쿼리 만든것 연결해야 함
		return mapper.search(keyword);
	}

	@Override
	public List<Board> list(Page page) throws Exception {
		
		// totalCount 조회
		int totalCount = mapper.count();
		// Page 객체에 totalCount 세팅
		page.setTotalCount(totalCount);
		// 페이징 처리하여 게시글 목록 조회
		List<Board> boardList = mapper.listWithPage(page);
		
		return boardList;
	}
	
	
}
