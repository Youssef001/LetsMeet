package zemmahi.youssef.letsmeet;

import android.graphics.Bitmap;
import android.util.Base64;

import java.nio.ByteBuffer;

/**
 * Created by mahdi on 16-03-10.
 */
public class ImageCoder {
    private int longueur_;
    Bitmap bitmap_;
    /* Constructeur */
    ImageCoder(){}
    /* Convertir une photo en base64 */
    private String bitmapToBase64(Bitmap bitmap) {

        final int lnth= bitmap.getByteCount();
        ByteBuffer dst= ByteBuffer.allocate(lnth);
        bitmap.copyPixelsToBuffer(dst);
        byte[] byteArray=dst.array();

        byte[] ret = new byte[(bitmap.getByteCount()) * 3/4 + 54];
        for (int i =0; i<54; ++i) {
            ret[i] = byteArray[i];
        }
        int j = 54;
        for (int i=0; i<bitmap.getByteCount(); i+=4) {
            ret[j++] = byteArray[i+2];
            ret[j++] = byteArray[i+1];
            ret[j++] = byteArray[i];
        }
        return Base64.encodeToString(ret, Base64.NO_WRAP);
    }
}
