package com.example.spring02_jpademo.part01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring02_jpademo.part01.entity.MemEntity;

@Repository
public interface MemRepository extends JpaRepository<MemEntity, Integer>, MemRepositoryCustom{

	//JPQL (Java Persistence Query Language)
	@Query(value = "SELECT m FROM MemEntity m WHERE m.name = :name")
	List<MemEntity> findByNameJPQL(@Param("name") String name);
	
	//Native Query
	@Query(value = "SELECT * FROM mem WHERE name=:name",nativeQuery = true)
	List<MemEntity> findByNameNative(@Param("name")String name);
	
}
