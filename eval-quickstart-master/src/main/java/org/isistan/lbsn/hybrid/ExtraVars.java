package org.isistan.lbsn.hybrid;

import org.lenskit.inject.Parameter;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * The blending weight parameter for blending item scorers.
 */
@Qualifier
@Parameter(Boolean.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface ExtraVars {
}
