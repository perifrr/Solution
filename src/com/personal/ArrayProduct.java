package com.personal;

import java.util.Arrays;

import com.personal.util.LinkedList;

/**
 * Created by prajeev on 20/3/17.
 */
public class ArrayProduct {
    public int[] computeProductArray(int[] target){
        int[] productArray = new int[target.length];
        int grossProduct = 1;
        int nonZeroProduct = 1;
        int nonZeroNumbers = 0;

        for (int i = 0,j = target.length-1; i < target.length; i++,j--) {
            if(i<=j){
                if(i==j){
                    productArray[i] = grossProduct;
                    grossProduct = grossProduct*target[i];
                    continue;
                }
                if(target[i]!=0 && target[j]!=0) {
                    grossProduct = grossProduct * target[i] * target[j];
                    nonZeroProduct = nonZeroProduct * target[i] * target[j];
                } else {
                    grossProduct = 0;
                    if(nonZeroNumbers > 1 || (target[i]==0 && target[j]==0)){
                        return productArray;
                    }
                    if(target[i]==0 && target[j]!=0){
                        nonZeroProduct = nonZeroProduct*target[j];
                        nonZeroNumbers++;
                    } else if(target[i]!=0 && target[j]==0){
                        nonZeroProduct = nonZeroProduct*target[i];
                        nonZeroNumbers++;
                    }
                }
            } else {
                if(grossProduct == 0){
                    if(target[i]!=0) {
                        productArray[i] = 0;
                    }else {
                        productArray[i] = nonZeroProduct;
                    }

                    if(target[j]!=0) {
                        productArray[j] = 0;
                    }else {
                        productArray[j] = nonZeroProduct;
                    }

                } else {
                    productArray[i] = grossProduct / target[i];
                    productArray[j] = grossProduct / target[j];
                }

            }
        }
        return productArray;
    }

    public static void main(String[] args) {
        int[] target = {10,3,5,6,2};
        System.out.println(new LinkedList(target));
        int[] result = new ArrayProduct().computeProductArray(target);
        System.out.println(new LinkedList(result));
    }
}


/*
 * Nitin's solution
 * In the interview I gave this solution but I didn't handle the zero case properly.
 * Also used int instead of long.
 * 
 */
private long[] productsOfElements(int... nums) {
	
	final int N = nums.length;
	long[] prods = new long[N]; //long because result can be long after multiplying lot of ints.
	
	// get total product of array elements skipping 0s
	int product = 1;
	int num = 1;
	int zeroCount = 0;
	for (int i=0;i<N;i++) {
		num = nums[i];
		if (num==0) {
			zeroCount++;
			num = 1; //skip 0 multiplication
		}
		product = product * num;
	}
	
	//if there are 2 or more 0s in array, then everything is 0 in result
	if (zeroCount>1) {
		Arrays.fill(prods, 0);
		return (prods);
	}
	
	//if flow goes here then are 1 or none zeros in array.
	int divisor = 1;
	for (int i=0;i<N;i++) {
		divisor = nums[i];
		
		//if there is one zero somewhere in the array, then all the other products array will be zero except the value in that index
		if (zeroCount>0) { 
			if (divisor==0) {  //if current element is zero then don't divide by zero
				prods[i] = product;
			}
			else {
				prods[i]=0;
			}
		}
		else { //if flow goes here, then array has no zeros. Solution is simple. Divide by element in that index.
			prods[i] = product/divisor;
		}
		
	}
	
	return prods;
}

