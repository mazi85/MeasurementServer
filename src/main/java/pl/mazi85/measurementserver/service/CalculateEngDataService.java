package pl.mazi85.measurementserver.service;

import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.model.SampleDef;

import java.text.DecimalFormat;

@Service
public class CalculateEngDataService {

    private static final Integer MAX_REG_16BIT_VALUE = 65535;
    private static final Integer MIN_REG_16BIT_VALUE = 0;

    public Double getEngValue(SampleDef sampleDef, Integer rawValue){
        Double engValue=0.0;
        if (rawValue >MIN_REG_16BIT_VALUE && rawValue< MAX_REG_16BIT_VALUE){
            double a = ((double)rawValue/MAX_REG_16BIT_VALUE);
            engValue=
                    (a*(sampleDef.getHighRange()-sampleDef.getLowRange()))+sampleDef.getLowRange();
        }
        else if (rawValue >= MAX_REG_16BIT_VALUE) {
            engValue= sampleDef.getHighRange();
        } else {
            engValue = sampleDef.getLowRange();
        }

        engValue =  ((double)Math.round(engValue * 100) / 100);

        return engValue;
    }



}
