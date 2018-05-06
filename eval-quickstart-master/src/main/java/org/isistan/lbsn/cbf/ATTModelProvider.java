package org.isistan.lbsn.cbf;

import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Provider;

import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.inject.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATTModelProvider implements Provider<ATTModel>{

	   private static final Logger logger = LoggerFactory.getLogger(ATTModelProvider.class);

	    private final DataAccessObject dao;

	    /**
	     * Construct a model builder.  The {@link Inject} annotation on this constructor tells LensKit
	     * that it can be used to build the model builder.
	     *
	     * @param dao The data access object.
	     */
	    @Inject
	    public ATTModelProvider(@Transient DataAccessObject dao) {
	        this.dao = dao;
	    }

	    /**
	     * This method is where the model should actually be computed.
	     * @return The TF-IDF model (a model of item tag vectors).
	     */
	    @Override
	    public ATTModel get() {
	        logger.info("Building ATT model");
	        
	        Map<Long, Map<String, Double>> modelData = new HashMap<>();
			for (Entity ent : dao.streamEntities(CommonTypes.ITEM)) {
				  long itemId = Long.parseLong(ent.get("id").toString());
				  String att_line = ent.get("att").toString();
				  Map<String, Double> attWork = new HashMap<>();
				  for(String att :att_line.split(Pattern.quote("|"))){
						  String [] val = att.split("=");
						  double valor = 0.0;
						  if(val[1].equalsIgnoreCase("True"))
							  valor = 1.0;
						  
						  attWork.put(val[0], valor);
				  }
				  modelData.put(itemId, attWork);
			}
	        logger.info("Computed Att vectors for {} items", modelData.size());
	        return new ATTModel(modelData);
	    }

}
