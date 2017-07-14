package com.isistan.lbsn.preproceso;

import java.util.Random;
import java.util.stream.IntStream;

import org.apache.mahout.common.RandomUtils;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.google.common.collect.Streams;


public class MainEjemplo {
	  private static Random random;
	public static void main(String[] args) {
			System.out.println("INICIO - MainGenerarTrainTest -");
			
//			random = RandomUtils.getRandom();
//			int cant = 1000;
//			double suma = 0;
//			for (int i = 0; i < cant; i++) {
//				
//				if(random.nextDouble()< 0.7){
//					suma++;
//				}
//			}
//			System.out.println(suma/(cant));
//			random = RandomUtils.getRandom();
//			int cant2 = 10;
//			double suma2 = 0;
//			for (int i = 0; i < cant2; i++) {
//				if(random.nextDouble()< 0.7){
//					suma2++;
//				}
//			}
//			System.out.println(suma2/(cant2));
//			double visi  = Double.NaN;
//			
//			visi = (Double.isNaN(visi))?0:visi;
//			double a = 0.8*visi+ 0.9;
//			System.out.println("hollll"+ a);
			
//			IntStream.range(0, 100000000).parallel().forEach(i->{
//				int res = getRandomNumberInRange(i,i+1);
//				System.out.println(res);
//				
//			}
//			);
			
		    ICombinatoricsVector<Long> initialVector = Factory.createVector(
		    	      new Long[] { 1L, 2L, 3L} );
		    	   // Create a simple combination generator to generate 3-combinations of the initial vector
		    	   Generator<Long> gen =  Factory.createSimpleCombinationGenerator(initialVector, 2);
		    	   Streams.stream( gen.iterator()).parallel().forEach( i ->{
		    		   
		    		   System.out.println(i.getValue(0));
		    	   }
		    			);
		    	  
//		    	   // Print all possible combinations
//		    	   for (ICombinatoricsVector<Long> combination : gen) {
//		    	      System.out.println(combination);
//		    	   }

}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	}
