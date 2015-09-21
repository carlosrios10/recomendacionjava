package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.SamplingLongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioUsuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Properties prop = new Properties();
    	InputStream input = null;
 
    	try {
    		//	HashMap<Long, Long> resumenSola =  new HashMap<Long, Double>();
    			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
    			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
    			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
    			ScoringCercaniaUsuarioUsuario scoring = new ScoringCercaniaUsuarioUsuario(null, null, userModel, itemModel);
    		    LongPrimitiveIterator usersIterable = ratingModel.getUserIDs(); 
    		    LongPrimitiveIterator usersIterable2 = ratingModel.getUserIDs(); 
    		    int cant = 0;
    		    while (usersIterable.hasNext()) {
    		    	long userID = usersIterable.next();
    		    	cant++;
    		    	System.out.println("1 :" + userID + "cant :" + cant);
    		    	usersIterable2.skip(cant);
    		    	while(usersIterable2.hasNext()){
    		    		long otherUserID = usersIterable2.next();
    		    		double theSimilarity = scoring.userSimilarity(userID, otherUserID);
    		    		
    		    	}
    		        }
 
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
 
    }
    }
