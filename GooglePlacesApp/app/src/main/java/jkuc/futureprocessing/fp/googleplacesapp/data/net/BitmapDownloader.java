package jkuc.futureprocessing.fp.googleplacesapp.data.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import rx.Observable;
import rx.Subscriber;

public class BitmapDownloader {

    public Observable<Bitmap> getBitmap(final String photoRef){
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                subscriber.onNext(getImage(photoRef));
                subscriber.onCompleted();
            }
        });
    }

    private Bitmap getImage(String photoRef) {
        Bitmap bmp = null;
        try {
            URL url = new URL(BuildConfig.PHOTO_URL + photoRef +"&key=" + BuildConfig.PLACES_API_KEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            //return rounded bitmap
            bmp = roundBitmap(BitmapFactory.decodeStream(is));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private Bitmap roundBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(200,
                                            200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, 200,
                                   200);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(200 / 2,
                          200 / 2, 100, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(getResizedBitmap(bitmap, 200, 200), rect, rect, paint);
        return output;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
