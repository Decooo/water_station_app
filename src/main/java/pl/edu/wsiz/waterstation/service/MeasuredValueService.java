package pl.edu.wsiz.waterstation.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.dto.ValueDTO;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;
import pl.edu.wsiz.waterstation.repository.MeasuredValueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasuredValueService {

	private final MeasuredValueRepository measuredValueRepository;

	@Autowired
	public MeasuredValueService(MeasuredValueRepository measuredValueRepository) {
		this.measuredValueRepository = measuredValueRepository;
	}

	public ValueDTO getLastValue() throws Exception {
		Optional<MeasuredValue> measuredValue = measuredValueRepository.getLastValue();
		if (measuredValue.isPresent())
			return new ModelMapper().map(measuredValue.get(), ValueDTO.class);
		else throw new Exception("Not found last value");
	}

	public List<ValueDTO> getIntervalValues(String dateFrom, String dateTo) {
		List<MeasuredValue> measuredValues = measuredValueRepository.getIntervalValues(dateFrom, dateTo);
		return new ModelMapper().map(measuredValues, new TypeToken<List<MeasuredValue>>() {
		}.getType());
	}

	public List<ValueDTO> getLast100Values() {
		List<MeasuredValue> measuredValues = measuredValueRepository.getLast100Values();
		return new ModelMapper().map(measuredValues, new TypeToken<List<ValueDTO>>() {
		}.getType());
	}
}
