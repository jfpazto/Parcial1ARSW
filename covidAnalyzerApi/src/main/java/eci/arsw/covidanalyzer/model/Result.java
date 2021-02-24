package eci.arsw.covidanalyzer.model;

public class Result {
	public String idPerson;
	public String nombre;
	public double resultType;
	
	public Result(String idPerson, String nombre,double resultType) {
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
	public double getResultType() {
		return resultType;
	}
	public void setResultType(double resultType) {
		this.resultType = resultType;
	}
	
}
