package com.example.spring01.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

@Repository // 서버가 startup될 때 이 클래스가 메모리에 자동으로 등록됨
public class MemberDAOImpl implements MemberDAO {
	
	// 로깅 처리를 위한 객체 선언
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// SqlSession 객체를 개발자가 직접 생성하지 않고 스프링에서 연결시켜줌
	@Inject// 의존관계 주입
	SqlSession sqlSession;
	
//	==========================<회원 목록>==========================
	@Override
	public List<MemberDTO> memberList() {
		logger.info("내가 => memberList called...");
		//sql mapper에 작성된 sql 코드가 실행됨(auto commit & close)
		return sqlSession.selectList("member.memberList");
	}
//	==========================<회원추가>==========================
	@Override
	public void insertMember(MemberDTO vo) {
		sqlSession.insert("member.insertMember", vo);
	} 
//	==========================<해당 아이디의 db값 갖고 오기>==========================
	@Override
	public MemberDTO viewMember(String userid) {
		// 변수를 받아서 기능구현 코딩을 추가로 안해 줄것이라 return으로 바로 넘김!
		return sqlSession.selectOne("member.viewMember", userid);
	}
//	==========================<id, pw가 db 유무 확인>==========================
	@Override
	public boolean checkPw(String userid, String passwd) {
		boolean result = false;
		// mapper에 넘길 값이 2개 이상인 경우 map으로 묶어서 전달
		Map<String, String> map= new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		int count = sqlSession.selectOne("member.checkPw", map);
		// 리턴값이 1이면 true, 0이면 false
		if(count==1) result=true;
		
		return result;
	}
	
//	==========================<삭제 버튼 db로>==========================
	@Override
	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid);
	}
//	==========================<회원정보 수정 db로>==========================
	@Override
	public void updateMember(MemberDTO vo) {
		sqlSession.update("member.updateMember", vo);
	}



}
