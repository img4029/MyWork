package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional
@Repository
public class MyRepositoryImpl implements MyRepository {
	
	private final EntityManager em;
	
	public MyRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	//@Override
	public List<Member> emMemberList2() {
		
		return em.createQuery("Select m from Member m order by m.id asc", Member.class)
			.getResultList();
		// => JPQL 적용
		// => "Select * from Member order by id asc" 500 오류 발생
		//    antlr.NoViableAltException: unexpected token: * ~~~
		//    Entity 통해 접근하기 때문에 * 사용금지, 앨러이스를 통해 컬럼명 접근
 	}

	//Parameter 적용
	@Override
	public List<Member> emMemberList() {
		
		return em.createQuery("Select m from Member m where m.jno<:jno order by m.id asc", Member.class)
				.setParameter("jno", 7)
				.getResultList();
 	}
	
	@Override
	public Member emMemberDetail(String id) {
		
		return em.createQuery("select m from Member m where m.id = :id", Member.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
