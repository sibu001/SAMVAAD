package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.MyClass;
import com.divergent.meet.divergentmeet.repository.MyClassRepository;
import com.divergent.meet.divergentmeet.service.MyClassService;

@Service
public class MyClassServiceImpl implements MyClassService {

	@Autowired
	private MyClassRepository myClassRepository;

	@Override
	public MyClass create(@Valid MyClass myClass) {
		MyClass myClassOld = myClassRepository.findByName(myClass.getName());
		if (myClassOld != null) {
			throw new GenricException("Class name already used");
		}
		myClass = myClassRepository.save(myClass);

		return myClass;
	}

	@Override
	public MyClass update(@Valid MyClass myClass) {

		Optional<MyClass> myClassOldId = myClassRepository.findById(myClass.getId());
		if (!myClassOldId.isPresent()) {
			throw new GenricException("Class not found");
		}
		MyClass myClassOldName = myClassRepository.findByName(myClass.getName());
		if (myClassOldName != null && !myClassOldName.getId().equals(myClass.getId())) {
			throw new GenricException("Class name already used");
		}
		myClass = myClassRepository.save(myClass);

		return myClass;

	}

	@Override
	public List<MyClass> getAll() {
		return myClassRepository.findAll();
	}

	@Override
	public MyClass get(Long id) {
		Optional<MyClass> myClassOldId = myClassRepository.findById(id);
		if (!myClassOldId.isPresent()) {
			throw new GenricException("Class not found");
		}
		return myClassOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		myClassRepository.deleteById(id);
		
	}

}
