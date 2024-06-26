/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqtp;

public class sqtpJNI {
  public final static native void SqtpSubframe_t_payload_set(long jarg1, SqtpSubframe_t jarg1_, long jarg2);
  public final static native long SqtpSubframe_t_payload_get(long jarg1, SqtpSubframe_t jarg1_);
  public final static native void SqtpSubframe_t_id_set(long jarg1, SqtpSubframe_t jarg1_, int jarg2);
  public final static native int SqtpSubframe_t_id_get(long jarg1, SqtpSubframe_t jarg1_);
  public final static native void SqtpSubframe_t_length_set(long jarg1, SqtpSubframe_t jarg1_, long jarg2);
  public final static native long SqtpSubframe_t_length_get(long jarg1, SqtpSubframe_t jarg1_);
  public final static native long new_SqtpSubframe_t();
  public final static native void delete_SqtpSubframe_t(long jarg1);
  public final static native void SqtpFrameReader_t_frame_set(long jarg1, SqtpFrameReader_t jarg1_, long jarg2);
  public final static native long SqtpFrameReader_t_frame_get(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native void SqtpFrameReader_t_length_set(long jarg1, SqtpFrameReader_t jarg1_, long jarg2);
  public final static native long SqtpFrameReader_t_length_get(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native void SqtpFrameReader_t_index_set(long jarg1, SqtpFrameReader_t jarg1_, long jarg2);
  public final static native long SqtpFrameReader_t_index_get(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native void SqtpFrameReader_t_status_set(long jarg1, SqtpFrameReader_t jarg1_, int jarg2);
  public final static native int SqtpFrameReader_t_status_get(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native void SqtpFrameReader_t_subframe_set(long jarg1, SqtpFrameReader_t jarg1_, long jarg2, SqtpSubframe_t jarg2_);
  public final static native long SqtpFrameReader_t_subframe_get(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native long new_SqtpFrameReader_t();
  public final static native void delete_SqtpFrameReader_t(long jarg1);
  public final static native void SqtpFrameWriter_t_frame_set(long jarg1, SqtpFrameWriter_t jarg1_, long jarg2);
  public final static native long SqtpFrameWriter_t_frame_get(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native void SqtpFrameWriter_t_length_set(long jarg1, SqtpFrameWriter_t jarg1_, long jarg2);
  public final static native long SqtpFrameWriter_t_length_get(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native void SqtpFrameWriter_t_index_set(long jarg1, SqtpFrameWriter_t jarg1_, long jarg2);
  public final static native long SqtpFrameWriter_t_index_get(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native void SqtpFrameWriter_t_status_set(long jarg1, SqtpFrameWriter_t jarg1_, int jarg2);
  public final static native int SqtpFrameWriter_t_status_get(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native long new_SqtpFrameWriter_t();
  public final static native void delete_SqtpFrameWriter_t(long jarg1);
  public final static native long sqtpFrameGetLength(long jarg1);
  public final static native int sqtpFrameReaderInit(long jarg1, SqtpFrameReader_t jarg1_, byte[] jarg2, long jarg3);
  public final static native int sqtpFrameReaderReset(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native int sqtpFrameReaderNext(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native int sqtpFrameWriterInit(long jarg1, SqtpFrameWriter_t jarg1_, byte[] jarg2, long jarg3);
  public final static native int sqtpFrameWriterReset(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native int sqtpFrameWriterWriteDirect(long jarg1, SqtpFrameWriter_t jarg1_, int jarg2, byte[] jarg3, long jarg4);
  public final static native int sqtpFrameWriterWriteSubframe(long jarg1, SqtpFrameWriter_t jarg1_, long jarg2, SqtpSubframe_t jarg2_);
  public final static native int sqtpFrameWriterFinalize(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native long sqtpFrameReaderInitStruct(byte[] jarg1, long jarg2);
  public final static native long sqtpFrameReaderResetStruct(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native long sqtpFrameReaderNextStruct(long jarg1, SqtpFrameReader_t jarg1_);
  public final static native long sqtpFrameWriterInitStruct(byte[] jarg1, long jarg2);
  public final static native long sqtpFrameWriterWriteStructDirect(long jarg1, SqtpFrameWriter_t jarg1_, int jarg2, byte[] jarg3, long jarg4);
  public final static native long sqtpFrameWriterWriteStructSubframe(long jarg1, SqtpFrameWriter_t jarg1_, long jarg2, SqtpSubframe_t jarg2_);
  public final static native long sqtpFrameWriterFinalizeStruct(long jarg1, SqtpFrameWriter_t jarg1_);
  public final static native int SqspFrameByteArray(byte[] jarg1, int jarg2, long jarg3, SqtpFrameWriter_t jarg3_);
  public final static native int SqtpCheckValue_tSize();
  public final static native int SQTP_STATUS_SUCCESS_get();
  public final static native int SQTP_STATUS_FAILED_get();
  public final static native int SQTP_STATUS_FRAME_END_get();
  public final static native int SQTP_STATUS_FRAME_BAD_FORMAT_get();
  public final static native int SQTP_STATUS_FRAME_BAD_LENGTH_get();
  public final static native int SQTP_STATUS_FRAME_BAD_CHECK_get();
  public final static native int SQTP_STATUS_SUBFRAME_BAD_FORMAT_get();
  public final static native int SQTP_STATUS_SUBFRAME_BAD_ID_get();
  public final static native int SQTP_STATUS_SUBFRAME_BAD_LENGTH_get();
  public final static native int SQTP_STATUS_SUBFRAME_BAD_CHECK_get();
  public final static native int SQTP_ID_RESERVED_0000_get();
  public final static native int SQTP_ID_RESERVED_FFFF_get();
  public final static native int SQTP_ID_LEGACY_REQUEST_get();
  public final static native int SQTP_ID_LEGACY_RESPONSE_get();
  public final static native int SQTP_ID_RTCM_get();
  public final static native int SQTP_ID_SITEPOINT_OTA_MTU_get();
  public final static native int SQTP_ID_SITEPOINT_OTA_UPDATE_get();
  public final static native int SQTP_ID_SITEPOINT_OTA_STATUS_get();
  public final static native int SQTP_ID_SITEPOINT_RTCM_get();
  public final static native int SQTP_ID_SITEPOINT_STATUS_COMPACT_get();
  public final static native int SQTP_ID_SITEPOINT_LLA_COMPACT_get();
  public final static native int SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG_COMPACT_get();
  public final static native int SQTP_ID_SITEPOINT_RELPOS_COMPACT_get();
  public final static native int SQTP_ID_SITEPOINT_HOSTMCU_STATUS_get();
  public final static native int SQTP_ID_SITEPOINT_BLE_STATUS_get();
  public final static native int SQTP_ID_SITEPOINT_STATUS_get();
  public final static native int SQTP_ID_SITEPOINT_LLA_get();
  public final static native int SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG_get();
  public final static native int SQTP_ID_SITEPOINT_RELPOS_get();
  public final static native int SQTP_ID_UBLOX_get();
  public final static native int GetSubFrameByteArray(long jarg1, SqtpFrameReader_t jarg1_, byte[] jarg2);
}
