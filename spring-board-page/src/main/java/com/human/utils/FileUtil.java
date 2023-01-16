package com.human.utils;

import java.io.File;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component			// 빈 등록 -- fileSeviceImpl에서 의존주입 받아 재사용 가능
public class FileUtil {
	
	/**
	 * 
	 * 파일 삭제
	 * @param filePath
	 * @return
	 */
	public boolean delete(String filePath) {
		
	File deleteFile = new File(filePath);
		
		// 2. 파일 존재여부 확인
		if( deleteFile.exists()) {
			if( deleteFile.delete()) {
				log.info("파일 삭제 성공!");
				// DB의 파일정보 삭제 
				return true;
			}
			else {
				log.info("파일 삭제 실패!");
				log.info("파일 : " + filePath);
			}
		} else {
			log.info("파일이 존재하지 않습니다.");
			log.info("파일 : " + filePath);
		}
		
		// 파일 삭제 실패
		return false;
		
	}
	
}