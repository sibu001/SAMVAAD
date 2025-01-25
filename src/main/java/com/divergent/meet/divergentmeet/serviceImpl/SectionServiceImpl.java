package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.Section;
import com.divergent.meet.divergentmeet.repository.SectionRepository;
import com.divergent.meet.divergentmeet.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionRepository sectionRepository;

	@Override
	public Section create(@Valid Section section) {

		Section sectionOld = sectionRepository.findByName(section.getName());
		if (sectionOld != null) {
			throw new GenricException("Section name already used");
		}
		section = sectionRepository.save(section);

		return section;
	}

	@Override
	public Section update(@Valid Section section) {

		Optional<Section> sectionOldId = sectionRepository.findById(section.getId());
		if (!sectionOldId.isPresent()) {
			throw new GenricException("Section not found");
		}
		Section sectionOldName = sectionRepository.findByName(section.getName());
		if (sectionOldName != null && !sectionOldName.getId().equals(section.getId())) {
			throw new GenricException("Section name already used");
		}
		section = sectionRepository.save(section);

		return section;

	}

	@Override
	public List<Section> getAll() {
		// TODO Auto-generated method stub
		return sectionRepository.findAll();
	}

	@Override
	public Section get(Long id) {
		Optional<Section> sectionOldId = sectionRepository.findById(id);
		if (!sectionOldId.isPresent()) {
			throw new GenricException("Section not found");
		}
		return sectionOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		
		sectionRepository.deleteById(id);

	}
}
