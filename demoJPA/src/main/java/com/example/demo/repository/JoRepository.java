package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Jo;

public interface JoRepository extends JpaRepository<Jo, Integer>{

	@Query(value = "select A.jno, A.jname, A.captain, B.name, A.project, A.slogan "
			+ "from jo A LEFT JOIN member B on A.captain = B.id WHERE A.jno=:jno" , nativeQuery = true)
	public Jo findByJno(@Param("jno")int jno);
	
}
