/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.ntrip;

public final class Error_Status_t {
  public final static Error_Status_t Error_Status_Unknown = new Error_Status_t("Error_Status_Unknown", ntripJNI.Error_Status_Unknown_get());
  public final static Error_Status_t Error_Status_0 = new Error_Status_t("Error_Status_0", ntripJNI.Error_Status_0_get());
  public final static Error_Status_t Error_Status_200 = new Error_Status_t("Error_Status_200", ntripJNI.Error_Status_200_get());
  public final static Error_Status_t Error_Status_401 = new Error_Status_t("Error_Status_401", ntripJNI.Error_Status_401_get());
  public final static Error_Status_t Error_Status_404 = new Error_Status_t("Error_Status_404", ntripJNI.Error_Status_404_get());
  public final static Error_Status_t Error_Status_409 = new Error_Status_t("Error_Status_409", ntripJNI.Error_Status_409_get());
  public final static Error_Status_t Error_Status_500 = new Error_Status_t("Error_Status_500", ntripJNI.Error_Status_500_get());
  public final static Error_Status_t Error_Status_501 = new Error_Status_t("Error_Status_501", ntripJNI.Error_Status_501_get());
  public final static Error_Status_t Error_Status_503 = new Error_Status_t("Error_Status_503", ntripJNI.Error_Status_503_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static Error_Status_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + Error_Status_t.class + " with value " + swigValue);
  }

  private Error_Status_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private Error_Status_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private Error_Status_t(String swigName, Error_Status_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static Error_Status_t[] swigValues = { Error_Status_Unknown, Error_Status_0, Error_Status_200, Error_Status_401, Error_Status_404, Error_Status_409, Error_Status_500, Error_Status_501, Error_Status_503 };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

