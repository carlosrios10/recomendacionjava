package com.isistan.lbsn.scoring;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.collections.IteratorUtils;
import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.entidades.Item;

public class ScoringOverlapAsFirstItemCategory implements Scoring{
	DataModel dataModel;
	ItemModel itemModel;
	DataModelByItemCategory dataModelItemCate;
	int filtro;
	public ScoringOverlapAsFirstItemCategory(DataModel dataModel, ItemModel itemModel) {
		this.dataModel=dataModel;
		this.itemModel=itemModel;
	}
	
	public ScoringOverlapAsFirstItemCategory(DataModel dataModel, ItemModel itemModel,DataModelByItemCategory dataModelItemCate) {
		this.dataModel=dataModel;
		this.itemModel=itemModel;
		this.dataModelItemCate = dataModelItemCate;
		int cant = dataModelItemCate.getCantidadCategorias();
		
	}
	
	
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
	//	System.out.println(userID1+" "+userID2+" "+itemID);
		Item item = itemModel.getItem(itemID);
		double maxScoring = 0.0;
		for(Long idCategoria :item.getCategoriaNivel1()){
			try{
				
				FastIDSet xPrefs = dataModelItemCate.getItemIDsFromUser(userID1,idCategoria);
				FastIDSet yPrefs = dataModelItemCate.getItemIDsFromUser(userID2,idCategoria);
				double scoringCategoria = calcularScoring(xPrefs, yPrefs);
				if (Double.isNaN(scoringCategoria)){
					maxScoring = Double.NaN;
				}else{
					if(scoringCategoria > maxScoring)
						maxScoring = scoringCategoria; 
				}
			}catch(NoSuchUserException noUser){
				maxScoring = Double.NaN;
			}
		}
	    
	    return maxScoring;
	}

	private double calcularScoring(FastIDSet xPrefsCategoria,
			FastIDSet yPrefsCategoria) {
		int xPrefsSize = xPrefsCategoria.size();
	    int yPrefsSize = yPrefsCategoria.size();
	    if (xPrefsSize == 0 && yPrefsSize == 0) {
	      return Double.NaN;
	    }
	    if (xPrefsSize == 0 || yPrefsSize == 0) {
	      return 0.0;
	    }
	    
	    int intersectionSize =
	        xPrefsSize < yPrefsSize ? yPrefsCategoria.intersectionSize(xPrefsCategoria) : xPrefsCategoria.intersectionSize(yPrefsCategoria);
	    if (intersectionSize == 0) {
	      return Double.NaN;
	    }
	    
	    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
	    
	    return (double) intersectionSize/ (double) unionSize;
	}
	
	private LongPrimitiveArrayIterator mergeUserIDs(long itemID) throws TasteException{
		Collection<Long> cateNivelTodas = new HashSet<Long>(); 
		Item item = itemModel.getItem(itemID);
		Collection<Long> coll = item.getCategoriaNivel1();
		for (Iterator<Long> iterator = coll.iterator(); iterator.hasNext();) {
			Long long1 = iterator.next();
			LongPrimitiveIterator userIDs = this.dataModelItemCate.getUserIDs(long1);
			cateNivelTodas.addAll(IteratorUtils.toList(userIDs));
		} 
		long[] catArray = Longs.toArray(cateNivelTodas);
		return new LongPrimitiveArrayIterator(catArray);
	}
	
	
	public FastIDSet filtrarPorCategoria(FastIDSet itemIds, long theItemID){
	    Collection<Long> itemFiltrados = new HashSet<Long>();
		Item theItem = itemModel.getItem(theItemID);
		Collection<Long> theItemCategoriaNivel1 = theItem.getCategoriaNivel1();
		
		for (Long itemId : itemIds) {
			Item item = itemModel.getItem(itemId);
			Collection<Long> categoriaNivel1 = item.getCategoriaNivel1();
			categoriaNivel1.retainAll(theItemCategoriaNivel1);
			if (categoriaNivel1.size()>0){
				itemFiltrados.add(itemId);
			}
		}
		FastIDSet result = new FastIDSet(itemFiltrados.size());
		long[] catArray = Longs.toArray(itemFiltrados);
		result.addAll(catArray);
		return result;
	}

}
