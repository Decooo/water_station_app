package pl.edu.wsiz.waterstation.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.dto.LastValueDTO;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;
import pl.edu.wsiz.waterstation.repository.MeasuredValueRepository;

@Service
public class MeasuredValueService {

	private final MeasuredValueRepository measuredValueRepository;

	@Autowired
	public MeasuredValueService(MeasuredValueRepository measuredValueRepository) {
		this.measuredValueRepository = measuredValueRepository;
	}

	public LastValueDTO getLastValue() {
		MeasuredValue measuredValue = measuredValueRepository.getLastValue();
		return new ModelMapper().map(measuredValue, LastValueDTO.class);
	}
}
