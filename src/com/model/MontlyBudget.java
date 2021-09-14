package com.model;

public class MontlyBudget extends BudgetVO {

	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getUsedBudget() {
		return usedBudget;
	}

	public void setUsedBudget(double usedBudget) {
		this.usedBudget = usedBudget;
	}

	public double getCostProduct() {
		return costProduct;
	}

	public void setCostProduct(double costProduct) {
		this.costProduct = costProduct;
	}

	private double usedBudget;
	
	
	private double costProduct;

}
