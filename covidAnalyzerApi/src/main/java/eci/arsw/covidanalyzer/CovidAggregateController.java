package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.CovidAggregateServiceStub;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import eci.arsw.covidanalyzer.service.ResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidAggregateController {
    @Autowired
    CovidAggregateServiceStub service;
    ICovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addTruePositiveResult(@RequestBody Result result) {
        //TODO
        try {
        	ResultType resta=ResultType.TRUE_POSITIVE;
        	service.aggregateResult(result, resta);
			
		} catch (ResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addTrueNegativeResult(@RequestBody Result result) {
        //TODO
        try {
        	ResultType resta=ResultType.TRUE_NEGATIVE;
        	service.aggregateResult(result, resta);
			
		} catch (ResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addFalseNegativeResult(@RequestBody Result result) {
        //TODO
        try {
        	ResultType resta=ResultType.FALSE_POSITIVE;
        	service.aggregateResult(result, resta);
			
		} catch (ResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addFalsePositiveResult(@RequestBody Result result) {
        //TODO
        try {
        	ResultType resta=ResultType.FALSE_POSITIVE;
        	service.aggregateResult(result, resta);
			
		} catch (ResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getTruePositiveResult() throws ResultException {
    	ResultType result=ResultType.TRUE_POSITIVE;
        return new ResponseEntity<>(service.getResult(result), HttpStatus.ACCEPTED);

    }
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getTrueNegativeResult() throws ResultException {
    	ResultType result=ResultType.TRUE_NEGATIVE;
        return new ResponseEntity<>(service.getResult(result), HttpStatus.ACCEPTED);

    }


    //TODO: Implemente el método.

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity savePersonaWithMultipleTests() {
        //TODO
        covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return null;
    }
    
}