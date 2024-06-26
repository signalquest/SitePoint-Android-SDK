/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class SqspLla_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqspLla_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqspLla_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqspLla_t obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  @SuppressWarnings({"deprecation", "removal"})
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        sqspJNI.delete_SqspLla_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setITOW(int value) {
    sqspJNI.SqspLla_t_iTOW_set(swigCPtr, this, value);
  }

  public int getITOW() {
    return sqspJNI.SqspLla_t_iTOW_get(swigCPtr, this);
  }

  public void setReserved(int value) {
    sqspJNI.SqspLla_t_reserved_set(swigCPtr, this, value);
  }

  public int getReserved() {
    return sqspJNI.SqspLla_t_reserved_get(swigCPtr, this);
  }

  public void setLon(double value) {
    sqspJNI.SqspLla_t_lon_set(swigCPtr, this, value);
  }

  public double getLon() {
    return sqspJNI.SqspLla_t_lon_get(swigCPtr, this);
  }

  public void setLat(double value) {
    sqspJNI.SqspLla_t_lat_set(swigCPtr, this, value);
  }

  public double getLat() {
    return sqspJNI.SqspLla_t_lat_get(swigCPtr, this);
  }

  public void setHeight(double value) {
    sqspJNI.SqspLla_t_height_set(swigCPtr, this, value);
  }

  public double getHeight() {
    return sqspJNI.SqspLla_t_height_get(swigCPtr, this);
  }

  public void setHMSL(double value) {
    sqspJNI.SqspLla_t_hMSL_set(swigCPtr, this, value);
  }

  public double getHMSL() {
    return sqspJNI.SqspLla_t_hMSL_get(swigCPtr, this);
  }

  public void setHAcc(double value) {
    sqspJNI.SqspLla_t_hAcc_set(swigCPtr, this, value);
  }

  public double getHAcc() {
    return sqspJNI.SqspLla_t_hAcc_get(swigCPtr, this);
  }

  public void setVAcc(double value) {
    sqspJNI.SqspLla_t_vAcc_set(swigCPtr, this, value);
  }

  public double getVAcc() {
    return sqspJNI.SqspLla_t_vAcc_get(swigCPtr, this);
  }

  public SqspLla_t() {
    this(sqspJNI.new_SqspLla_t(), true);
  }

}
