package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.MyClass;

public interface MyClassService {

	MyClass create(@Valid MyClass myClass);

	MyClass update(@Valid MyClass myClass);

	List<MyClass> getAll();

	MyClass get(Long id);

	void delete(@Valid Long id);

}
