package com.human.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.domain.Files;
import com.human.mapper.FileMapper;
import com.human.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService{

	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public int insert(Files file) throws Exception {
		
		return fileMapper.insert(file);
	}

	@Override
	public List<Files> listByParentNo(Files file) throws Exception {
		return fileMapper.listByParentNo(file);
	
	}

	@Override
	public Files read(int fileNo) throws Exception {
		return fileMapper.read(fileNo);
	}

	@Override
	public int delete(int fileNo) throws Exception {
		
		// 실제 파일 삭제 
		// 1. DB 에서 파일 경로를 조회해온다.(경로를 알아야한다.)
		Files file = fileMapper.read(fileNo);
		
		String filePath = file.getFilePath();
		File deleteFile = new File(filePath);
		
		boolean result = fileUtil.delete(filePath);
		
		if(result) {
			return fileMapper.delete(fileNo);
		}
		return 0;
		
		/*// 파일 존재여부 확인
		if( deleteFile.exists() ) {
			if( deleteFile.delete() ) {
				log.info("파일 삭제 성공!");
				// DB의 파일정보 삭제
				return fileMapper.delete(fileNo);
			}
			else {
				log.info("파일 삭제 실패...");
				log.info("파일 : " + filePath);
			}
		}else {
			log.info("파일이 존재하지 않습니다");
			log.info("파일 : " + filePath);
			//fileMapper.delete(fileNo);
		}
		
		// 파일 삭제 실패 
		return 0;
	*/	
	}
	
	
}
