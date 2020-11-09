package com.maemresen.fxregisterapp.constants;

import java.lang.annotation.*;

/**
 * @author emresen
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {
    String message();
}