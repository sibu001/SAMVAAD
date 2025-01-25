package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.MyClass;

@Repository
public interface MyClassRepository extends JpaRepository<MyClass, Long> {

	public MyClass findByName(String name);


}
