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
	List<Result> resti;
	
	public CovidAggregateServiceStub() {
		resti=new CopyOnWriteArrayList<>();
		ResultType result=ResultType.TRUE_POSITIVE;
		Result r1=new Result("1","Pablo",result);
		Result r2=new Result("2","Pedro",result);
		Result r3=new Result("3","Gonzalo",result);
		
		
		resti.add(r1);
		resti.add(r2);
		resti.add(r3);
		
		
		
	}

	@Override
	public boolean aggregateResult(Result result, ResultType type) throws ResultException {
		for (Result elemento:resti)
		{
			if(elemento.getIdPerson().equals(result.idPerson))
			{
				throw new ResultException("cuenta ya creada");
			}
		}
		resti.add(result);
		return true;
	}

	@Override
	public List<Result> getResult(ResultType type) {
		List<Result> restfin=new CopyOnWriteArrayList<>();
		for (Result elemento:resti)
		{
			if(elemento.getResultType().equals(type))
			{
				restfin.add(elemento);
			}
		}
		return restfin;
		
	}

	@Override
	public void upsertPersonWithMultipleTests(String id, ResultType type) {
		for (Result elemento:resti)
		{
			if(elemento.getIdPerson().equals(id))
			{
				elemento.setResultType(type);
			
			}
		}
	}

}
