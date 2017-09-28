package ren.yale.android.baseutilslib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Yale on 2016/3/30.
 */
public class BitmapUtils {

    public static Bitmap compressQuality(Bitmap image,int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    public static Point getBitmapWidthHeight(String dstPath){
        BitmapFactory.Options opts = null;
        opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(dstPath, opts);
        return new Point(opts.outWidth,opts.outHeight);
    }

    public static Bitmap getBitmapFromFile(String dstPath, int width, int height) {

        return getBitmapFromFile(new File(dstPath),width,height);
    }

    public static Bitmap getBitmapFromFile(File dst, int width, int height) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                        width * height);
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
                opts.inPreferredConfig= Bitmap.Config.RGB_565;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
    public static boolean saveBitmap2JPEG(Bitmap bmp,String path,int quality){

        try {
            if (bmp!=null) {
                FileOutputStream fos = new FileOutputStream(path);
                bmp.compress(Bitmap.CompressFormat.JPEG, quality, fos);
                fos.flush();
                fos.close();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static Bitmap resize(Bitmap bmp, float scale){
        try {
            Matrix matrix = new Matrix();
            matrix.postScale(scale,scale);
            Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);

            return resizeBmp;
        } catch (Exception e) {

        }
        return null;

    }
    public static Bitmap resizeSqure(Bitmap bmp, int w){
        if (bmp == null) {
            return null;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.postScale((float)w/bmp.getWidth(),(float)w/bmp.getHeight());
            Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);

            return resizeBmp;
        } catch (Exception e) {
        }
        return null;

    }
    public static Bitmap resize(Bitmap bmp, int w, int h){
        if (bmp == null) {
            return null;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.postScale((float)w/bmp.getWidth(),(float)h/bmp.getHeight());
            Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);

            return resizeBmp;
        } catch (Exception e) {
        }
        return null;

    }
}



