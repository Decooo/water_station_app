package pl.edu.wsiz.waterstation.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.dto.ValueDTO;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;
import pl.edu.wsiz.waterstation.repository.MeasuredValueRepository;

import java.util.List;

@Service
public class MeasuredValueService {

	private final MeasuredValueRepository measuredValueRepository;

	@Autowired
	public MeasuredValueService(MeasuredValueRepository measuredValueRepository) {
		this.measuredValueRepository = measuredValueRepository;
	}

	public ValueDTO getLastValue() {
		MeasuredValue measuredValue = measuredValueRepository.getLastValue();
		return new ModelMapper().map(measuredValue, ValueDTO.class);
	}

	public List<ValueDTO> getIntervalValues(String dateFrom, String dateTo) {
		List<MeasuredValue> measuredValues = measuredValueRepository.getIntervalValues(dateFrom, dateTo);
		return new ModelMapper().map(measuredValues, new TypeToken<List<MeasuredValue>>() {}.getType());
	}
}
