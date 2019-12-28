package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject //스프링 컨테이너가 만든 dao 객체가 연결됨(의존관계 주입)
	MemberDAO memberDao;
	
	
	
	
//	==========================<회원목록>==========================
	@Override
	public List<MemberDTO> memberList() {
		return memberDao.memberList();
	}
	
//	==========================<회원추가>==========================
	@Override
	public void insertMember(MemberDTO dto) {
		memberDao.insertMember(dto);
	}
	
//	==========================<특정 ID의 값을 받아 오기>==========================
	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid);
	}
//	==========================<id, pw가 db 유무 확인>==========================
	@Override
	public boolean checkPw(String userid, String passwd) {
		return memberDao.checkPw(userid, passwd);
	}
	
//	==========================<삭제 버튼>==========================
	@Override
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);
	}
	

//	==========================<회원정보 수정>==========================
	@Override
	public void updateMember(MemberDTO dto) {
		memberDao.updateMember(dto);
	}

}
