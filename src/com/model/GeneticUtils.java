package com.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * The Class GeneticUtils.
 */
public class GeneticUtils {

	private static final char ONES = '1';

	/**
	 * Convert to binary.
	 * 
	 * @param number
	 *            the number
	 * @return the string
	 */
	public static String convertToBinary(int number) {
		return Integer.toBinaryString(number);
	}

	/**
	 * Binary to int.
	 * 
	 * @param binary
	 *            the binary
	 * @return the int
	 */
	public static int binaryToInt(String binary) {
		return Integer.parseInt(binary, 2);
	}

	/**
	 * Checks if is number.
	 * 
	 * @param isNumber
	 *            the is number
	 * @return true, if is number
	 */
	public static boolean isNumber(final String isNumber) {
		return isNumber.matches("[+-]?\\d*(\\.\\d+)?");
	}

	/**
	 * Convert float to binary.
	 * 
	 * @param f
	 *            the f
	 * @return the string
	 */
	public static String convertFloatToBinary(float f) {
		int intBits = Float.floatToIntBits((float) f);
		String binary = Integer.toBinaryString(intBits);
		return binary;
	}

	/**
	 * Convert binary to float.
	 * 
	 * @param binary
	 *            the binary
	 * @return the float
	 */
	public static float convertBinaryToFloat(String binary) {
		int intBits1 = Integer.parseInt(binary, 2);
		float myFloat = Float.intBitsToFloat(intBits1);
		return myFloat;
	}

	/**
	 * Convert all numbers to binary.
	 * 
	 * @param fs
	 *            the fs
	 * @return the list
	 */
	public static List<String> convertAllNumbersToBinary(float... fs) {
		List<String> binaryList = new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {
			binaryList.add(convertFloatToBinary(fs[i]));
		}
		return binaryList;
	}

	/**
	 * Convert double to binary.
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String convertDoubleToBinary(double value) {
		return Long.toBinaryString(Double.doubleToRawLongBits(value));
	}
	
	
	public static void main(String s[]){
		
		
		CrossOverObj crossOverObj=obtainCrossOverForPaths(600.75,507.83,Arrays.asList(101.57,52.58,123.58,120.67,507.83,600.75),600.75);
		
		System.out.println("================ Orginal Paths =============================");
		System.out.println(crossOverObj.getOrginalPaths());
		
		System.out.println("================== Four paths =============================");
		System.out.println(crossOverObj.getFourPaths());
		
		System.out.println("============== MAximum Ones Path =================");
		System.out.println(crossOverObj.getMaxOnesPath());
		
		System.out.println("============== COunt of Ones for Genes =================");
		System.out.println(crossOverObj.getCountOfOnesFourPaths());
		
		System.out.println("Muatation ==========================================");
		System.out.println(crossOverObj.getMutatedPath());
		
		System.out.println("Muatation index==========================================");
		System.out.println(crossOverObj.getMutatedIndex());
		
		System.out.println("================= Distance ===================");
		System.out.println(crossOverObj.getDistances());
		
		System.out.println("================= Best Distance ======================");
		System.out.println(crossOverObj.getMinDistance()); 
		
		System.out.println("====================Best Page ========");
		System.out.println(crossOverObj.getBestPage());
		
		
		
		
		
		
	}
	
	

	/**
	 * Convert binary to double.
	 * 
	 * @param text
	 *            the text
	 * @return the double
	 */
	public static double convertBinaryToDouble(String text) {
		double doubleVal = Double.longBitsToDouble(new BigInteger(text, 2)
				.longValue());
		return doubleVal;
	}

	/**
	 * Convert all numbers to binary.
	 * 
	 * @param fs
	 *            the fs
	 * @return the list
	 */
	public static List<String> convertAllDoublesToBinary(double... fs) {
		List<String> binaryList = new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {
			binaryList.add(convertDoubleToBinary(fs[i]));
		}
		return binaryList;
	}

	/**
	 * Convert all binary to doubles.
	 * 
	 * @param s
	 *            the s
	 * @return the list
	 */
	public static List<Double> convertAllBinaryToDoubles(String... s) {
		List<Double> doubleList = new ArrayList<Double>();
		for (int i = 0; i < s.length; i++) {
			doubleList.add(convertBinaryToDouble(s[i]));
		}
		return doubleList;
	}

	/**
	 * Compute cost.
	 * 
	 * @param c1
	 *            the c1
	 * @param c2
	 *            the c2
	 * @param c3
	 *            the c3
	 * @param c4
	 *            the c4
	 * @param c5
	 *            the c5
	 * @param c6
	 *            the c6
	 * @param p1
	 *            the p1
	 * @param p2
	 *            the p2
	 * @param p3
	 *            the p3
	 * @param p4
	 *            the p4
	 * @param p5
	 *            the p5
	 * @param p6
	 *            the p6
	 * @return the double
	 */
	public double computeCost(double c1, double c2, double c3, double c4,
			double c5, double p1, double p2, double p3, double p4, double p5) {
		double cost = c1 * p1 + c2 * p2 + c3 * p3 + c4 * p4 + c5 * p5;
		return cost;
	}

	/**
	 * Obtain the two best costs.
	 * 
	 * @param costMap
	 *            the cost map
	 * @return the map
	 */
	public List<PageInfo> findTwoBestCosts(Map<Double, Integer> costMap,
			List<Integer> pageNames) {

		List<PageInfo> pageInfoList = new ArrayList<PageInfo>();

		List<Double> costList = new ArrayList<Double>();

		List<Integer> pageNamesListfromMap = new ArrayList<Integer>();

		Iterator entries = costMap.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			Double cost = (Double) thisEntry.getKey();
			Integer value = (Integer) thisEntry.getValue();
			pageNamesListfromMap.add(value);
			costList.add(cost);
		}

		Collections.sort(costList);

		Collections.reverse(costList);

		double page1Cost = 0;
		double page2Cost = 0;

		Integer pageName1 = null;
		Integer pageName2 = null;

		if (costMap.size() <= 1) {

			page1Cost = costList.get(0);

			pageName1 = costMap.get(page1Cost);

			pageName2 = removePageInMap(pageNames, pageNamesListfromMap);

			page2Cost = page1Cost;

		} else {

			page1Cost = costList.get(0);
			page2Cost = costList.get(1);

			pageName1 = costMap.get(page1Cost);
			pageName2 = costMap.get(page2Cost);

		}

		populatePageInfo(pageInfoList, page1Cost, page2Cost, pageName1,
				pageName2);

		return pageInfoList;

	}

	private static Integer removePageInMap(List<Integer> pageNames,
			List<Integer> pageNamesListfromMap) {
		Iterator<Integer> iterator = pageNames.listIterator();
		Integer pageName = null;
		while (iterator.hasNext()) {

			pageName = iterator.next();

			if (!pageNamesListfromMap.contains(pageName)) {
				break;
			}

		}
		return pageName;
	}

	private static void populatePageInfo(List<PageInfo> pageInfoList,
			double page1Cost, double page2Cost, Integer pageName1,
			Integer pageName2) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBestCost(page1Cost);
		pageInfo.setPageName(pageName1);

		pageInfoList.add(pageInfo);

		pageInfo = new PageInfo();
		pageInfo.setBestCost(page2Cost);
		pageInfo.setPageName(pageName2);

		pageInfoList.add(pageInfo);
	}

	/**
	 * Obtain cross over for paths.
	 *
	 * @param page1Cost
	 *            the page1 cost
	 * @param page2Cost
	 *            the page2 cost
	 * @param costList
	 *            the cost list
	 * @param normalizationFact
	 *            the normalization fact
	 * @return the cross over obj
	 */
	public static CrossOverObj obtainCrossOverForPaths(double page1Cost,
			double page2Cost, List<Double> costList, double normalizationFact) {

		CrossOverObj crossOverObj = new CrossOverObj();

		String binaryPath1 = convertDoubleToBinary(page1Cost
				/ (double) normalizationFact);

		String binaryPath2 = convertDoubleToBinary(page2Cost
				/ (double) normalizationFact);

		List<String> orginalPaths = new ArrayList<String>();

		orginalPaths.add(binaryPath1);
		orginalPaths.add(binaryPath2);
		crossOverObj.setOrginalPaths(orginalPaths);

		int lengthPath1 = binaryPath1.length();

		int lengthPath2 = binaryPath2.length();

		int halfLenPath1 = lengthPath1 / 2;

		int halfLenPath2 = lengthPath2 / 2;

		String subPart1OfPath1 = binaryPath1.substring(0, halfLenPath1);

		String subPart2OfPath1 = binaryPath1.substring(halfLenPath1 + 1,
				lengthPath1 - 1);

		String subPart1OfPath2 = binaryPath2.substring(0, halfLenPath2);

		String subPart2OfPath2 = binaryPath2.substring(halfLenPath2 + 1,
				lengthPath2 - 1);

		String newPath1 = subPart1OfPath1 + subPart2OfPath2;

		String newPath2 = subPart1OfPath2 + subPart2OfPath1;

		List<String> fourPaths = new ArrayList<String>();
		fourPaths.add(binaryPath1);
		fourPaths.add(binaryPath2);
		fourPaths.add(newPath1);
		fourPaths.add(newPath2);
		crossOverObj.setFourPaths(fourPaths);

		// Now Compute the No of Ones1 of Each Path
		List<Integer> counterList = new ArrayList<Integer>();
		Map<Integer, Integer> mapCountPathIndex = new HashMap<Integer, Integer>();
		int k = 0;
		for (String tempPath : fourPaths) {
			int count = computeCountForCharInStr(tempPath, ONES);
			counterList.add(count);
			mapCountPathIndex.put(count, k);
			k = k + 1;
		}

		crossOverObj.setCountOfOnesFourPaths(counterList);
		// Find the Maximum value of Count
		int maxCount = Collections.max(counterList);

		// Find the Maximum Binary Path Index
		int tempKey = mapCountPathIndex.get(maxCount);

		// Maximum Binary Path Index is
		String maxOnesPath = fourPaths.get(tempKey);

		crossOverObj.setMaxOnesPath(maxOnesPath);

		// Now Perform Mutation on the Path

		int len = maxOnesPath.length();

		Random r = new Random();
		int mutatedIndex = r.nextInt(len - 1);

		crossOverObj.setMutatedIndex(mutatedIndex);

		// Now Find the Mutation Path

		StringBuilder mutatedPath = new StringBuilder(maxOnesPath);
		mutatedPath.setCharAt(mutatedIndex, '1');
		crossOverObj.setMutatedPath(mutatedPath.toString());

		// Now Find the Double Value for the Mutated path and Measure Distance

		double mutatedPathCost = convertBinaryToDouble(mutatedPath.toString());

		crossOverObj.setMutatedPathCost(mutatedPathCost);

		// Now Bring in the Distance and do a Closest Match With Respect to the
		// Costs

		Map<Double, Integer> costMap = new HashMap<Double, Integer>();
		List<Double> distanceList = new ArrayList<Double>();

		for (int i = 0; i < costList.size(); i++) {

			double distance = 0;
			double tempCost = costList.get(i);
			if (tempCost > mutatedPathCost) {
				distance = tempCost - mutatedPathCost;
			} else {
				distance = mutatedPathCost - tempCost;
			}
			distanceList.add(distance);
			costMap.put(distance, i);
		}

		crossOverObj.setDistances(distanceList);

		// Find the Minimum Distance
		double minDistance = Collections.min(distanceList);
		crossOverObj.setMinDistance(minDistance);

		// Page of Minimum Distance to Push Advitisement is

		int pageIndex = costMap.get(minDistance);

		crossOverObj.setAdvistisementPageIndex(pageIndex);
		
		crossOverObj.setBestPage(pageIndex);

		return crossOverObj;

	}

	private static int computeCountForCharInStr(String str, char c) {
		int len = str.length();
		int count = 0;

		for (int i = 0; i < len; i++) {

			char s1 = str.charAt(i);

			
			if (s1 == c) {
				count = count + 1;
			}

		}
		return count;
	}

}
