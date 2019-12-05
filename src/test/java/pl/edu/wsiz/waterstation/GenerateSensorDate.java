package pl.edu.wsiz.waterstation;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wsiz.waterstation.dto.SaveMeasuredValueDTO;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;
import pl.edu.wsiz.waterstation.entity.Sensor;
import pl.edu.wsiz.waterstation.repository.MeasuredValueRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateSensorDate {

	@Autowired
	private MeasuredValueRepository measuredValueRepository;

	/*
	Aby wygerenrować dane należy przypisać wartości poniższym zmiennym, a następnie odpalić test generateSensorData
	 */

	private long sensorId = 1;

	private float maxValue = 30;
	private float minValue = 0;
	private float deviationValue = 0.3f;

	//Daty ustawione są w metodzie inicializującej
	private Date startDate;
	private Date stopDate;

	//czas między pomiarami, podawany w minutach
	private int deepSleep = 10;

	@Test
	void generateSensorData() {
		initData();
		Random random = new Random();
		float actualValue = randomStartValue(random);
		Date currentDate = startDate;

		while (currentDate.before(stopDate)) {
			saveMeasuredValue(currentDate, actualValue);
			actualValue = generateNewValue(actualValue, random);
			currentDate = addDate(currentDate);
		}
	}

	public void initData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String startDateInString = "04-12-2019 01:00:00";
		String stopDateInString = "04-12-2019 05:00:00";
		try {
			startDate = sdf.parse(startDateInString);
			stopDate = sdf.parse(stopDateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private float randomStartValue(Random random) {
		float actualValue = minValue + random.nextFloat() * (maxValue - minValue);
		actualValue = (float) (Math.round(actualValue * 100.0) / 100.0);
		return actualValue;
	}

	private void saveMeasuredValue(Date currentDate, float actualValue) {
		Sensor sensor = new Sensor();
		sensor.setSensorId(sensorId);
		SaveMeasuredValueDTO saveMeasuredValueDTO = new SaveMeasuredValueDTO(actualValue,
				new Timestamp(currentDate.getTime()),
				sensor);

		measuredValueRepository.save(new ModelMapper().map(saveMeasuredValueDTO, MeasuredValue.class));
	}

	private float generateNewValue(float currentValue, Random random) {
		float randomValue = -deviationValue + random.nextFloat() * (deviationValue + deviationValue);
		currentValue += randomValue;
		currentValue = (float) (Math.round(currentValue * 100.0) / 100.0);
		if (currentValue > maxValue)
			return maxValue;
		else if (currentValue < minValue)
			return minValue;
		else return currentValue;
	}

	private Date addDate(Date currentDate) {
		return DateUtils.addMinutes(currentDate, deepSleep);
	}
}
