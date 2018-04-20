package com.isistan.lbsn.util;

import java.util.ArrayList;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.recomendacionfc.Resultado;

public class Util {
	
	public static void exportarUsuarioDistanciasCsv(final UserModel userModel, String fileName) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/resultados/"+fileName+".csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("Id","distanciaTotalRecorrida","distanciaPromedioRecorrida","cantidadDeItem","distanciaTotalEntreItems");
				Iterator<Long> iterador = userModel.getUsuariosTabla().keySet().iterator();
				while (iterador.hasNext()){
					Long usuarioId =  iterador.next();
					User user = userModel.getUser(usuarioId);
		        	out.writeNext(
	        				Long.toString(user.getId()),
	        				Double.toString(user.getDistanciaTotalRecorrida()),
	        				Double.toString(user.getDistanciaPromedioRecorrida()),
	        				Integer.toString(user.getCantidadDeItem()),
	        				Double.toString(user.getDistanciaTotalEntreItems())
	        				);
					
				}
		        
		   }
		});
		
	}
	
	public static void exportarResultadoCsv(final ArrayList<Resultado> resultados, String fileName) {
		String resultadosPath = MyProperties.getInstance().getProperty("resultados");
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write(resultadosPath+fileName+".csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("Agregation","Scoring","Similitud",
		        			  "TVecinos","Nvecinos","Threshold",
		        			  "alfa","beta","Mae","Rms",
		        			  "Precision","Recall","F1Measure",
		        			  "Fallou","nDgg",
		        			  "Reach","numUserTest","userTestConVecinos","promedioVecinos","numPrefTest");
		        for (Resultado resultado : resultados) {
		        	out.writeNext(
		        				resultado.getConfiguracion().getAgregationType().toString(),
		        				resultado.getConfiguracion().getScoringType().toString(),
		        			    resultado.getConfiguracion().getSimAlg().toString(),
		        			    resultado.getConfiguracion().getTypeNeigh().toString(),
		        				Integer.toString(resultado.getConfiguracion().getNeighSize()),
		        				Double.toString(resultado.getConfiguracion().getThreshold()),
		        				Double.toString(resultado.getConfiguracion().getAlfa()),
		        				Double.toString(resultado.getConfiguracion().getBeta()),
		        				Double.toString(resultado.getScoreMae()),
		        				Double.toString(resultado.getScoreRms()),
		        				Double.toString(resultado.getScorePrecision()),
		        				Double.toString(resultado.getScoreRecall()),
		        				Double.toString(resultado.getScoreF1Measure()),
		        				Double.toString(resultado.getFallout()),
		        				Double.toString(resultado.getnDgg()),
		        				Double.toString(resultado.getReach()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadUsuariosTest()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadUsuarioTestConVecinos()),
		        				Double.toString(resultado.getResultadoCantV().getPromedioVecinos()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadPreferenciasTest())
		        				
		        				);
				}
		        
		   }
		});
		
	}
	public static void exportarResultadoCsvSVD(final ArrayList<Resultado> resultados, String fileName) {
		String resultadosPath = MyProperties.getInstance().getProperty("resultados");
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write(resultadosPath+fileName+".csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("modelo","numfeatures","Mae","Rms",
		        			  "Precision","Recall","F1Measure",
		        			  "Fallou","nDgg",
		        			  "Reach","numUserTest","userTestConVecinos","promedioVecinos","numPrefTest");
		        for (Resultado resultado : resultados) {
		        	out.writeNext(
		        			    resultado.getConfiguracion().getTypeModel().toString(),
		        				Integer.toString(resultado.getConfiguracion().getNumFeature()),
		        				Double.toString(resultado.getScoreMae()),
		        				Double.toString(resultado.getScoreRms()),
		        				Double.toString(resultado.getScorePrecision()),
		        				Double.toString(resultado.getScoreRecall()),
		        				Double.toString(resultado.getScoreF1Measure()),
		        				Double.toString(resultado.getFallout()),
		        				Double.toString(resultado.getnDgg()),
		        				Double.toString(resultado.getReach()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadUsuariosTest()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadUsuarioTestConVecinos()),
		        				Double.toString(resultado.getResultadoCantV().getPromedioVecinos()),
		        				Double.toString(resultado.getResultadoCantV().getCantidadPreferenciasTest())
		        				
		        				);
				}
		        
		   }
		});
		
	}
	
	private static void exportarCsv(final ArrayList<Resultado> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/resultados/resultadosUBCF_PR_NY_UE.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("Similitud","TVecinos","Nvecinos","Threshold","alfa","beta","Mae","Rms","Precision","Recall");
		        for (Resultado resultado : resultados) {
		        	out.writeNext(
		        			    resultado.getConfiguracion().getSimAlg().toString(),
		        			    resultado.getConfiguracion().getTypeNeigh().toString(),
		        				Integer.toString(resultado.getConfiguracion().getNeighSize()),
		        				Double.toString(resultado.getConfiguracion().getThreshold()),
		        				Double.toString(resultado.getConfiguracion().getAlfa()),
		        				Double.toString(resultado.getConfiguracion().getBeta()),
		        				Double.toString(resultado.getScoreMae()),
		        				Double.toString(resultado.getScoreRms()),
		        				Double.toString(resultado.getScorePrecision()),
		        				Double.toString(resultado.getScoreRecall())
		        				
		        				);
				}
		        
		   }
		});
		
	}
	
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {  
        //double earthRadius = 3958.75;//miles  
        double earthRadius = 6378.1;//kilometers  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));  
        double dist = earthRadius * c;  
  
        return dist;  
    } 
    public static double distance(double lat1, double lon1, double lat2, double lon2 ) {
		String unit = "K";
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
