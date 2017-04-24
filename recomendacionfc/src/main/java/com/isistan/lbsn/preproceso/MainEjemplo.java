package com.isistan.lbsn.preproceso;

import java.util.Random;

import org.apache.mahout.common.RandomUtils;

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
			double visi  = Double.NaN;
			
			visi = (Double.isNaN(visi))?0:visi;
			double a = 0.8*visi+ 0.9;
			System.out.println("hollll"+ a);

}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	}
