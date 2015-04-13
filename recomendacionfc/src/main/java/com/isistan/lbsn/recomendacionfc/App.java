package com.isistan.lbsn.recomendacionfc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.isistan.lbsn.config.MyProperties;

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
 
//    		String filename = "config.properties";
//    		
//    		//input = getClass().getClassLoader().getResourceAsStream(filename);
//    		input =  App.class.getClassLoader().getResourceAsStream(filename);
//    		if(input==null){
//    	            System.out.println("Sorry, unable to find " + filename);
//    		    return;
//    		}
// 
//    		//load a properties file from class path, inside static method
//    		prop.load(input);
// 
//                //get the property value and print it out
//                System.out.println(prop.getProperty("databaserating"));
//    	        System.out.println(prop.getProperty("databasegrafo"));
    	        
    	        System.out.println(MyProperties.getInstance().getProperty("databaserating"));
    	        System.out.println(MyProperties.getInstance().getProperty("databasegrafo"));
 
    	} 
//    	catch (IOException ex) {
//    		ex.printStackTrace();
//        } 
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
