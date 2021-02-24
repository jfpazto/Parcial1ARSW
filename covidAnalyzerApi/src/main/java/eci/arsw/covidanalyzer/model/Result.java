package eci.arsw.covidanalyzer.model;

public class Result {
	public String idPerson;
	public String nombre;
	public ResultType resultType;
	
	public Result(String idPerson, String nombre,ResultType resultType) {
		this.idPerson = idPerson;
		this.resultType = resultType;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}
	public ResultType getResultType() {
		return resultType;
	}
	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}
	
}
