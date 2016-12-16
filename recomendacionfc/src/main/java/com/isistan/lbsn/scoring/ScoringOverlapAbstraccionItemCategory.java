package com.isistan.lbsn.scoring;

import java.util.Collection;
import java.util.HashSet;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.entidades.Item;

public class ScoringOverlapAbstraccionItemCategory implements Scoring {
	DataModel dataModel;
	ItemModel itemModel;
	public ScoringOverlapAbstraccionItemCategory(DataModel dataModel, ItemModel itemModel) {
		this.dataModel=dataModel;
		this.itemModel=itemModel;
	}
	
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return getScoring(userID1, userID2, -1);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
	    FastIDSet xPrefs = dataModel.getItemIDsFromUser(userID1);
	    FastIDSet yPrefs = dataModel.getItemIDsFromUser(userID2);
	    
	    FastIDSet xPrefsCategoria = getCategoria(xPrefs,2);
	    FastIDSet yPrefsCategoria = getCategoria(yPrefs,2);
	    
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
	
	public FastIDSet getCategoria(FastIDSet itemIds, int nivel){
	    int size = itemIds.size();
	    FastIDSet result = new FastIDSet(size);
	    Collection<Long> cateNivelTodas = new HashSet<Long>(); 
		for (Long itemId : itemIds) {
			Item item = itemModel.getItem(itemId);
			Collection<Long> categoriaNivel = item.getCategoriaNivel2();
			cateNivelTodas.addAll(categoriaNivel);
		}
		
		long[] catArray = Longs.toArray(cateNivelTodas);
		result.addAll(catArray);
		return result;
	}

}
