package edu.bzu.soa.nutriserve.model;

public class Bmi {

	float weight;
	float length;
	float bmi;

	

	public Bmi(float weight2, float length2) {
		this.weight = weight2;
		this.length = length2;
		this.bmi = weight / length / length;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
		this.bmi = weight / length / length;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
		this.bmi = weight / length / length;
	}

	public float getBmi() {
		return bmi;
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

}
