package com.signalquest.api;

import static com.signalquest.swig.ntrip.Error_Status_t.Error_Status_200;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_NO_MESSAGE_PENDING;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_SUCCESS;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Types_t.NTRIP_RTCM_Message;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse_Init;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse_Next_Message;
import static com.signalquest.swig.ntrip.ntrip.getPollIndex;
import static com.signalquest.swig.ntrip.ntrip.getRTCMID;

import android.util.Log;

import androidx.annotation.NonNull;

import com.signalquest.api.utils.CircularByteBuffer;
import com.signalquest.api.utils.Logging;
import com.signalquest.api.utils.StableBuffer;
import com.signalquest.swig.ntrip.Error_Status_t;
import com.signalquest.swig.ntrip.NTRIP_Header_Info_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Context_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Poll_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Return_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Types_t;

import java.util.Arrays;
import java.util.List;

/**
 * Parses the NTRIP authorization and RTCM messages.
 * <p>
 * NTRIP responses can contain multiple and partial RTCM messages.
 * <p>
 * Only SitePoint-handled RTCM message types are reported.
 */
@SuppressWarnings("unused")
public class NtripParser {

    private final static String LOG_TAG = "SQ NTRIP";
    private static boolean initialized = false;
    private NTRIP_Parse_Context_t context;
    private final StableBuffer buffer = new StableBuffer(2 * 65536);
    private final CircularByteBuffer outBuffer = new CircularByteBuffer(16384);
    @SuppressWarnings("FieldCanBeLocal")
    private final boolean singleMessageMode = false;

    private final static List<Integer> allowedMessageTypes = Arrays.asList( // SitePoint recognizes these
            1001, // L1 - only GPS RTK observables
            1002, // Extended L1 - only GPS RTK observables
            1003, // L1 / L2 GPS RTK observables
            1004, // Extended L1 / L2 GPS RTK observables
            1005, // Stationary RTK reference station ARP
            1006, // Stationary RTK reference station ARP with antenna height
            1007, // Antenna descriptor
            1009, // L1 - only GLONASS RTK observables
            1010, // Extended L1 - only GLONASS RTK observables
            1011, // L1 / L2 GLONASS RTK observables
            1012, // Extended L1 / L2 GLONASS RTK observables
            1033, // Receiver and Antenna Description
            1074, // GPS MSM4
            1075, // GPS MSM5
            1077, // GPS MSM7
            1084, // GLONASS MSM4
            1085, // GLONASS MSM5
            1087, // GLONASS MSM7
            1094, // Galileo MSM4
            1095, // Galileo MSM5
            1097, // Galileo MSM7
            1124, // BeiDou MSM4
            1125, // BeiDou MSM5
            1127, // BeiDou MSM7
            1230, // GLONASS code - phase biases
            4072 // u-Blox Proprietary , 4072.0 Reference st
    );

    /**
     * Creates an NTRIP parser.
     */
    public NtripParser() {
        initialize();
    }

    private synchronized void initialize() {
        if (!initialized) {
            System.loadLibrary("ntrip_wrapper");
            context = new NTRIP_Parse_Context_t();
            NTRIP_Parse_Return_t libInitialized = NTRIP_Parse_Init(context, buffer.array(), buffer.capacity(), null);
            assert(NTRIP_SUCCESS == libInitialized);
            initialized = true;
        }
    }

    /**
     * Parses the RTCM messages.
     * <p>
     * Call after authorization is confirmed using {@link #parseAuthorized(byte[])}.
     * Results retrieved by calling {@link #next(int)}.
     *
     * @param data The bytes, after a successful authorization response, from the aiding NTRIP server
     *
     * @throws ParseException with a message to relay to SignalQuest.
     */
    @SuppressWarnings("unused")
    public void parseRtcm(@NonNull byte[] data) throws ParseException {
        NTRIP_Parse_Return_t status = NTRIP_Parse(context, data, data.length);
        if (status.swigValue() < 0) {
            throw new ParseException(data, status);
        }
    }

    /**
     * Parses an NTRIP server authorization response.
     *
     * @param data The initial response bytes from an NTRIP server aiding request
     *
     * @return AuthorizationResult
     *   <ol><li>INSUFFICIENT_DATA: Pass in the next response chunk.</li><li>SUCCESS: Authorization succeeded.</li></ol>
     *
     * @throws AuthorizationFailure containing detail on why the authorization failed.
     */
    @SuppressWarnings("unused")
    public AuthorizationResult parseAuthorized(@NonNull byte[] data) throws AuthorizationFailure {
        NTRIP_Parse_Return_t parsed = NTRIP_Parse(context, data, data.length);
        if (parsed.swigValue() < 0) {
            Log.w(LOG_TAG, "Error parsing authorization input stream: '" + Logging.asHex(data) + "'");
            throw new AuthorizationFailure(parsed.toString(), "Error parsing authorization input stream");
        }
        NTRIP_Header_Info_t header = context.getHeader();
        if (header.getAuthorized()) {
            return AuthorizationResult.SUCCESS;
        }
        boolean needMoreData = NTRIP_NO_MESSAGE_PENDING == parsed;
        if (needMoreData) {
            return AuthorizationResult.INSUFFICIENT_DATA;
        }

        NTRIP_Parse_Poll_t poll = context.getPoll();
        Error_Status_t status = header.getError_Status();
        String responseText = header.getFirst_HTTP_Reply_string();
        Log.w(LOG_TAG, String.format("Auth failed; %s; status/type: %s/%s (%s).", status, poll.getStatus(), poll.getType(), responseText));

        String reason = header.getShort_Text_string();
        String description = header.getDescription_string();
        throw new AuthorizationFailure(reason, description);
    }

    private void handleRtcmResults() {
        NTRIP_Parse_Poll_t poll = context.getPoll();
        NTRIP_Header_Info_t header = context.getHeader();
        NTRIP_Parse_Return_t status = poll.getStatus();
        NTRIP_Parse_Types_t type = poll.getType();

        //NOTE: getError_Status() will only update if a new header comes through.
        Error_Status_t httpStatus = header.getError_Status();
        if (httpStatus != Error_Status_200) {
            Log.w(LOG_TAG, "Unable to parse RTCM message " + header.getShort_Text_string() + ", " + header.getDescription_string());
            return;
        }
        if (status == NTRIP_MESSAGE_PENDING) {
            if (type == NTRIP_RTCM_Message) {
                final int start = (int) getPollIndex(context);
                final int length = (int) context.getPoll().getLength();
                byte[] rtcmMessage = new byte[length];
                System.arraycopy(buffer.array(), start, rtcmMessage, 0, length);
                int messageType = getRTCMID(rtcmMessage);
                if (messageType != -1 && allowedMessageTypes.contains(messageType)) {
                    outBuffer.put(rtcmMessage);
                    Log.d(LOG_TAG, "Processed RTCM message with id " + messageType);
                } else {
                    Log.d(LOG_TAG, "Unhandled message id " + messageType);
                }
            }
            NTRIP_Parse_Next_Message(context);
            if (!singleMessageMode) {
                handleRtcmResults();
            }
        } else if (status == NTRIP_NO_MESSAGE_PENDING) {
            Log.d(LOG_TAG, "parseRtcm done, no message pending");
        } else {
            Log.w(LOG_TAG, "parseRtcm neither pending nor not pending; please report to SignalQuest: status " + status + " and type " + type);
        }
    }

    public byte[] next(int maxLength) {
        handleRtcmResults();
        return outBuffer.get(maxLength);
    }

    /**
     * Detail for an NTRIP authorization failure.
     */
    public static class AuthorizationFailure extends Exception {
        public String summary;
        public String details;

        private AuthorizationFailure(String summary, String details) {
            this.summary = summary;
            this.details = details;
        }
    }

    public static class ParseException extends Exception {
        private ParseException(byte[] data, NTRIP_Parse_Return_t status) {
            super("Unexpected " + status.toString() + " while parsing '" + Logging.asHex(data) + "'");
        }
    }

    /**
     * Whether the parsed data was insufficient (send the next response chunk) or was
     * sufficient and correct for authorization.
     */
    public enum AuthorizationResult {
        INSUFFICIENT_DATA,
        SUCCESS,
    }

}
