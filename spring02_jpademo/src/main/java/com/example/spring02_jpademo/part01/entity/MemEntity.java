package com.example.spring02_jpademo.part01.entity;

import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "mem")
@Entity

@NamedQuery(
	    name = "MemEntity.findExpensive",
	    query = "SELECT m FROM MemEntity m WHERE m.name = :name"
	)

public class MemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_seq_generator")
	@SequenceGenerator(name = "mem_seq_generator", sequenceName = "mem_num_seq", allocationSize = 1)
	private int num;
	private String name;
	private int age;
	private String loc;
}
