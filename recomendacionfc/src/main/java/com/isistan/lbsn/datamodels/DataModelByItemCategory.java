package com.isistan.lbsn.datamodels;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.AbstractDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import com.isistan.lbsn.entidades.Item;

public class DataModelByItemCategory {
	DataModel dataModelAll;
	DataModel dataModelCate1;
	DataModel dataModelCate2;
	DataModel dataModelCate3;
	DataModel dataModelCate4;
	DataModel dataModelCate5;
	DataModel dataModelCate6;
	DataModel dataModelCate7;
	DataModel dataModelCate8;
	DataModel dataModelCateNivel2;
	int	ID_CATEGORIA_1 = 1;
	int	ID_CATEGORIA_2 = 41;
	int	ID_CATEGORIA_3 = 79;
	int	ID_CATEGORIA_4 = 159;
	int	ID_CATEGORIA_5 = 192;
	int	ID_CATEGORIA_6 = 236;
	int	ID_CATEGORIA_7 = 261;
	int	ID_CATEGORIA_8 = 325;
	ItemModel itemModel;
	int cantidadCategorias ;
	public DataModelByItemCategory(DataModel dataModel, ItemModel itemModel, int cantidadCategorias) throws TasteException{
		this.dataModelAll = dataModel;
		this.itemModel = itemModel;
		this.cantidadCategorias = cantidadCategorias;
		generarSplitByItemCategory();
		
	}

	private void generarSplitByItemCategory() throws TasteException{
		FastByIDMap<PreferenceArray> prefCat1 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat2 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat3 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat4 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat5 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat6 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat7 = new FastByIDMap<PreferenceArray>();
		FastByIDMap<PreferenceArray> prefCat8 = new FastByIDMap<PreferenceArray>();
		
		LongPrimitiveIterator userIDs = dataModelAll.getUserIDs();
		while (userIDs.hasNext()) {
			Long long1 = (Long) userIDs.next();
			PreferenceArray prefArray = dataModelAll.getPreferencesFromUser(long1);
			
			PreferenceArray prefArrayFil1 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_1);
			if( prefArrayFil1.length() != 0)
				prefCat1.put(long1, prefArrayFil1);
			
			PreferenceArray prefArrayFil2 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_2);
			if( prefArrayFil2.length() != 0)
				prefCat2.put(long1, prefArrayFil2);
			
			PreferenceArray prefArrayFil3 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_3);
			if( prefArrayFil3.length() != 0)
				prefCat3.put(long1, prefArrayFil3);
			
			PreferenceArray prefArrayFil4 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_4);
			if( prefArrayFil4.length() != 0)
				prefCat4.put(long1, prefArrayFil4);
			
			PreferenceArray prefArrayFil5 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_5);
			if( prefArrayFil5.length() != 0)
				prefCat5.put(long1, prefArrayFil5);
			
			PreferenceArray prefArrayFil6 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_6);
			if( prefArrayFil6.length() != 0)
				prefCat6.put(long1, prefArrayFil6);
			
			PreferenceArray prefArrayFil7 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_7);
			if( prefArrayFil7.length() != 0)
				prefCat7.put(long1, prefArrayFil7);
			
			PreferenceArray prefArrayFil8 =  filtrarPorCategoria(prefArray, this.ID_CATEGORIA_8);
			if( prefArrayFil8.length() != 0)
				prefCat8.put(long1, prefArrayFil8);
			
		}
		dataModelCate1 = new GenericDataModel(prefCat1);
		dataModelCate2 = new GenericDataModel(prefCat2);
		dataModelCate3 = new GenericDataModel(prefCat3);
		dataModelCate4 = new GenericDataModel(prefCat4);
		dataModelCate5 = new GenericDataModel(prefCat5);
		dataModelCate6 = new GenericDataModel(prefCat6);
		dataModelCate7 = new GenericDataModel(prefCat7);
		dataModelCate8 = new GenericDataModel(prefCat8);
		
	}
	
	public PreferenceArray filtrarPorCategoria(PreferenceArray prefArray , long cat){
		List<Preference> prefs2 = Lists.newArrayListWithCapacity(prefArray.length());
		for (Preference pref : prefArray) {
			prefs2.add(pref);
		}
		for (Iterator<Preference> iterator = prefs2.iterator(); iterator.hasNext();) {
				Preference pref = iterator.next();
				Item theItem = itemModel.getItem(pref.getItemID());
				if(!theItem.getCategoriaNivel1().contains(new Long(cat))){
					iterator.remove();
				}
			}
			
		return new GenericUserPreferenceArray(prefs2);

	}

	private DataModel getDataModelByCategory(long idCategory){
		if ( idCategory == this.ID_CATEGORIA_1 )
			return this.dataModelCate1;
		if ( idCategory == this.ID_CATEGORIA_2 )
			return this.dataModelCate2;
		if ( idCategory == this.ID_CATEGORIA_3 )
			return this.dataModelCate3;
		if ( idCategory == this.ID_CATEGORIA_4 )
			return this.dataModelCate4;
		if ( idCategory == this.ID_CATEGORIA_5 )
			return this.dataModelCate5;
		if ( idCategory == this.ID_CATEGORIA_6 )
			return this.dataModelCate6;
		if ( idCategory == this.ID_CATEGORIA_7 )
			return this.dataModelCate7;
		if ( idCategory == this.ID_CATEGORIA_8 )
			return this.dataModelCate8;
		return null;
	}
	
	public LongPrimitiveIterator getUserIDs(long idCategory) throws TasteException {
		return getDataModelByCategory(idCategory).getUserIDs();
	}

	public PreferenceArray getPreferencesFromUser(long userID,long idCategory)
			throws TasteException {
		return getDataModelByCategory(idCategory).getPreferencesFromUser(userID);
	}

	public FastIDSet getItemIDsFromUser(long userID,long idCategory) throws TasteException {
		return getDataModelByCategory(idCategory).getItemIDsFromUser(userID);
	}

	public LongPrimitiveIterator getItemIDs(long idCategory) throws TasteException {
		return getDataModelByCategory(idCategory).getItemIDs();
	}

	public PreferenceArray getPreferencesForItem(long itemID)
			throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Float getPreferenceValue(long userID, long itemID)
			throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getPreferenceTime(long userID, long itemID)
			throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumItems() throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumUsers() throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumUsersWithPreferenceFor(long itemID) throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumUsersWithPreferenceFor(long itemID1, long itemID2)
			throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreference(long userID, long itemID, float value)
			throws TasteException {
		// TODO Auto-generated method stub
		
	}

	public void removePreference(long userID, long itemID)
			throws TasteException {
		// TODO Auto-generated method stub
		
	}

	public boolean hasPreferenceValues() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public int getCantidadCategorias() {
		return cantidadCategorias;
	}

	public void setCantidadCategorias(int cantidadCategorias) {
		this.cantidadCategorias = cantidadCategorias;
	}

}
