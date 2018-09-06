package org.isistan.lbsn.weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class WekaTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 BufferedReader reader =
				   new BufferedReader(new FileReader("./data/train.arff"));
				 ArffReader arff = new ArffReader(reader);
				 Instances data = arff.getData();
				 data.setClassIndex(data.numAttributes() - 1);
				 
				 Random rand = new Random(1);  // using seed = 1
				 int folds = 5;
				 
//				 Classifier cls = new J48();
//				 Evaluation eval = new Evaluation(data);
//				 eval.crossValidateModel(cls, data, folds, rand);
//				 System.out.println(eval.toSummaryString());
//				 System.out.println(eval.toClassDetailsString());
				 
				 Logistic logis = new weka.classifiers.functions.Logistic();
				 Evaluation eval2 = new Evaluation(data);
				 eval2.crossValidateModel(logis, data, folds, rand);
				 System.out.println(eval2.toSummaryString());
				 System.out.println(eval2.toClassDetailsString());
				 logis.buildClassifier(data);
				 System.out.println(logis);

	}

}
