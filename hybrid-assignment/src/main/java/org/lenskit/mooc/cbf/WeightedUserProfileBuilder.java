package org.lenskit.mooc.cbf;

import org.lenskit.data.ratings.Rating;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Build a user profile from all positive ratings.
 */
public class WeightedUserProfileBuilder implements UserProfileBuilder {
    /**
     * The tag model, to get item tag vectors.
     */
    private final TFIDFModel model;

    @Inject
    public WeightedUserProfileBuilder(TFIDFModel m) {
        model = m;
    }

    @Override
    public Map<String, Double> makeUserProfile(@Nonnull List<Rating> ratings) {
        // Create a new vector over tags to accumulate the user profile
        Map<String,Double> profile = new HashMap<>();
        double cantRating = ratings.size();
        double sumRating = 0.0;
        for (Rating r: ratings) {
            sumRating = sumRating + r.getValue();
        }
        double ratingMean = sumRating/cantRating;

        for (Rating r: ratings) {
            double weights = r.getValue() - ratingMean;
                // TODO Get this item's vector and add it to the user's profile
                for (Map.Entry<String, Double> e : model.getItemVector(r.getItemId()).entrySet()) {
                    if(!profile.containsKey(e.getKey())){
                        profile.put(e.getKey(),0.0);
                    }
                    double profileValue = profile.get(e.getKey()) + (e.getValue()*weights);
                    profile.put(e.getKey(),profileValue);
                }

        }
        // TODO Normalize the user's ratings
        // TODO Build the user's weighted profile
        // The profile is accumulated, return it.
        return profile;
    }
}
