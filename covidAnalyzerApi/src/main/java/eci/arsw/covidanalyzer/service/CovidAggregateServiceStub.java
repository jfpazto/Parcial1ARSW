package eci.arsw.covidanalyzer.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

@Service
public class CovidAggregateServiceStub implements ICovidAggregateService {
	private final ConcurrentHashMap<ResultType,Result> resultados = new ConcurrentHashMap<>();
	
	public CovidAggregateServiceStub() {
		
		Result r1=new Result("1","Pablo",0.9);
		Result r2=new Result("2","Pedro",0.9);
		Result r3=new Result("3","Gonzalo",0.9);
		ResultType result=ResultType.TRUE_POSITIVE;
		
		aggregateResult(r1, result);
		//aggregateResult(r2, result);
		//aggregateResult(r3, result);
		
		
		
	}

	@Override
	public boolean aggregateResult(Result result, ResultType type) {
		resultados.put(type,result);
		return true;
	}

	@Override
	public Result getResult(ResultType type) {
		Result res=resultados.get(type);
		return res;
		
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		// TODO Auto-generated method stub
	}

}
