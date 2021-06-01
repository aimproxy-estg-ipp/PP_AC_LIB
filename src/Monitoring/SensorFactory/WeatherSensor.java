package Monitoring.SensorFactory;

import Monitoring.SensorFactory.Exceptions.UnrecognizedSensorParameter;
import edu.ma02.core.enumerations.Parameter;
import edu.ma02.core.enumerations.SensorType;
import edu.ma02.core.exceptions.MeasurementException;
import edu.ma02.core.exceptions.SensorException;

import java.time.LocalDateTime;

/*
 * Nome: Micael André Cunha Dias
 * Número: 8200383
 * Turma: LEI1T4
 *
 * Nome: Hugo Henrique Almeida Carvalho
 * Número: 8200590
 * Turma: LEI1T3
 */
class WeatherSensor extends Sensor {

    private final Parameter parameter;

    protected WeatherSensor(String sensorId,
                            double x, double y, double z,
                            double lat, double lng
    ) throws SensorException {
        super(sensorId, x, y, z, lat, lng);


        if ((parameter = identifySensorParameter(sensorId)) == null) {
            throw new UnrecognizedSensorParameter();
        }
    }

    @Override
    protected Parameter identifySensorParameter(String sensorId) {

        for (Parameter param : SensorType.WEATHER.getParameters()) {
            if (sensorId.contains(param.toString())) return param;
        }

        return null;
    }

    @Override
    public SensorType getType() {
        return SensorType.WEATHER;
    }

    @Override
    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public boolean addMeasurement(double value, LocalDateTime localDateTime, String unit) throws SensorException, MeasurementException {
        return super.addElement(new Measurement(value, localDateTime, unit, parameter));
    }
}