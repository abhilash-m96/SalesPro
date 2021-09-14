package com.model;


import java.io.Serializable;
import java.util.List;

public class CrossOverObj implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<String> getFourPaths() {
        return fourPaths;
    }

    public void setFourPaths(List<String> fourPaths) {
        this.fourPaths = fourPaths;
    }

    public List<String> getOrginalPaths() {
        return orginalPaths;
    }

    public void setOrginalPaths(List<String> orginalPaths) {
        this.orginalPaths = orginalPaths;
    }

    public List<Integer> getCountOfOnesFourPaths() {
        return countOfOnesFourPaths;
    }

    public void setCountOfOnesFourPaths(List<Integer> countOfOnesFourPaths) {
        this.countOfOnesFourPaths = countOfOnesFourPaths;
    }



    public Double getBestPageDistance() {
        return bestPageDistance;
    }

    public void setBestPageDistance(Double bestPageDistance) {
        this.bestPageDistance = bestPageDistance;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public void setDistances(List<Double> distances) {
        this.distances = distances;
    }

    public double getMaxCostPathValue() {
        return maxCostPathValue;
    }

    public void setMaxCostPathValue(double maxCostPathValue) {
        this.maxCostPathValue = maxCostPathValue;
    }

    public String getMaxOnesPath() {
        return maxOnesPath;
    }

    public void setMaxOnesPath(String maxOnesPath) {
        this.maxOnesPath = maxOnesPath;
    }

    public String getMutatedPath() {
        return mutatedPath;
    }

    public void setMutatedPath(String mutatedPath) {
        this.mutatedPath = mutatedPath;
    }

    public int getMutatedIndex() {
        return mutatedIndex;
    }

    public void setMutatedIndex(int mutatedIndex) {
        this.mutatedIndex = mutatedIndex;
    }

    private List<String> fourPaths;
    
    private List<String> orginalPaths;
    
    private List<Integer> countOfOnesFourPaths;
    
    private Integer bestPage;
    
    private Double bestPageDistance;
    
    private List<Double> distances;
    
    private double maxCostPathValue;
    
    private String maxOnesPath;
    
    private String mutatedPath;
    
    private int mutatedIndex;
    
    private double mutatedPathCost;

    public double getMutatedPathCost() {
        return mutatedPathCost;
    }

    public void setMutatedPathCost(double mutatedPathCost) {
        this.mutatedPathCost = mutatedPathCost;
    }
    
    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public int getAdvistisementPageIndex() {
        return advistisementPageIndex;
    }

    public void setAdvistisementPageIndex(int advistisementPageIndex) {
        this.advistisementPageIndex = advistisementPageIndex;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBestPage() {
		return bestPage;
	}

	public void setBestPage(Integer bestPage) {
		this.bestPage = bestPage;
	}

	private double minDistance;
    
    private int advistisementPageIndex;
    
    private String userId;
    
    
    
    

}
