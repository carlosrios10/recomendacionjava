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
public class ThresholdUserProfileBuilder implements UserProfileBuilder {
    /**
     * The lowest rating that will be considered in the user's profile.
     */
    private static final double RATING_THRESHOLD = 3.5;

    /**
     * The tag model, to get item tag vectors.
     */
    private final TFIDFModel model;

    @Inject
    public ThresholdUserProfileBuilder(TFIDFModel m) {
        model = m;
    }

    @Override
    public Map<String, Double> makeUserProfile(@Nonnull List<Rating> ratings) {
        // Create a new vector over tags to accumulate the user profile
        Map<String,Double> profile = new HashMap<>();

        // Iterate over the user's ratings to build their profile
        for (Rating r: ratings) {
            if (r.getValue() >= RATING_THRESHOLD) {
                // TODO Get this item's vector and add it to the user's profile
                for (Map.Entry<String, Double> e : model.getItemVector(r.getItemId()).entrySet()) {
                    if(!profile.containsKey(e.getKey())){
                        profile.put(e.getKey(),0.0);
                    }
                    double profileValue = profile.get(e.getKey()) + e.getValue();
                    profile.put(e.getKey(),profileValue);
                }


            }
        }

        // The profile is accumulated, return it.
        return profile;
    }
}
