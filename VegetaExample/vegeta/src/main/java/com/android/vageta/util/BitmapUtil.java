package com.android.vageta.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author tanhuohui
 */
public class BitmapUtil {

    public static Bitmap getBitmap(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false;

        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    public static Bitmap getBitmapFromUri(Context cxt, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    cxt.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
//            LogUtil.e("[Android]" + e.getMessage());
//            LogUtil.e("[Android]" + "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                                : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] getImageContentInSize(String pathName,
                                               int boundsWidth, int boundsHeight) {
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);

        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        if (srcWidth > boundsWidth || srcHeight > boundsHeight) {

            double boundsRatio = 1.0 * boundsWidth / boundsHeight;
            double srcRatio = 1.0 * srcWidth / srcHeight;
            double scaleRatio;
            if (boundsRatio > srcRatio) {
                scaleRatio = 1.0 * boundsHeight / srcHeight;
            } else {
                scaleRatio = 1.0 * boundsWidth / srcWidth;
            }
            int scaleWidth = (int) (srcWidth * scaleRatio);
            int scaleHeight = (int) (srcHeight * scaleRatio);

            bitmap = Bitmap.createScaledBitmap(bitmap, scaleWidth, scaleHeight,
                    false);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static byte[] decodeBitmap(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 1024 * 800);
        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        opts.inTempStorage = new byte[16 * 1024];
        FileInputStream is = null;
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
            double scale = getScaling(opts.outWidth * opts.outHeight,
                    1024 * 600);
            Bitmap bmp2 = Bitmap.createScaledBitmap(bmp,
                    (int) (opts.outWidth * scale),
                    (int) (opts.outHeight * scale), true);
            bmp.recycle();
            baos = new ByteArrayOutputStream();
            bmp2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp2.recycle();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }
        return baos.toByteArray();
    }

    private static double getScaling(int src, int des) {
        /**
         * 48 目标尺寸÷原尺寸 sqrt开方，得出宽高百分比 49
         */
        double scale = Math.sqrt((double) des / (double) src);
        return scale;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {

        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

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
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
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

    public static Bitmap corp(Bitmap bitmap, int CORP_THUMBS_WIDTH, int CORP_THUMBS_HEIGHT) {

        int corpWith = CORP_THUMBS_WIDTH;
        int corpHeight = CORP_THUMBS_HEIGHT;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int srcLeft = 0;
        int srcTop = 0;
        int dstLeft = 0;
        int dstTop = 0;

        Bitmap output = Bitmap.createBitmap(corpWith, corpHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        if (corpWith >= width) {
            dstLeft = (corpWith - width) / 2;
            corpWith = width;
        } else {
            srcLeft = (width - corpWith) / 2;
        }
        if (corpHeight >= height) {
            dstTop = (corpHeight - height) / 2;
            corpHeight = height;
        } else {
            srcTop = (height - corpHeight) / 2;
        }

//        LogUtil.d("corpWith:" + corpWith + ",corpHeight:" + corpHeight + ",dstLeft:" + dstLeft + ",dstTop:" + dstTop);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect dstRect = new Rect(dstLeft, dstTop, corpWith + dstLeft, corpHeight + dstTop);
        final Rect srcRect = new Rect(srcLeft, srcTop, corpWith + srcLeft, corpHeight + srcTop);
        final RectF rectF = new RectF(dstRect);
        final float roundPx = 0; //圆角像素  

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);

        return output;
    }

    /**
     * @param bitmap     原图
     * @param edgeLength 希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */

    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength, int HedgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();
        int scaledHeight = 0;
        int scaledWidth = 0;
        int longerEdge;
        if (widthOrg > edgeLength && heightOrg > HedgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int tempedgeLength = 0;
            if (edgeLength > HedgeLength) {
                tempedgeLength = edgeLength;
                longerEdge = tempedgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg);
                if (widthOrg > heightOrg) {
                    scaledWidth = widthOrg > heightOrg ? longerEdge : tempedgeLength;
                    scaledHeight = widthOrg > heightOrg ? longerEdge : HedgeLength;
                } else {
                    scaledWidth = widthOrg > heightOrg ? longerEdge : tempedgeLength;
                    scaledHeight = widthOrg > heightOrg ? tempedgeLength : longerEdge;
                }
            }
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
//            LogUtil.d("-releaseImageViewResouce--bitmap---------" + bitmap);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }



    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 800, 480);

        options.inPreferredConfig = Config.RGB_565;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);

    }


    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 2;
//        if (height > reqHeight || width > reqWidth) {
//            final int heightRatio = Math.round((float) height / (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
        return inSampleSize;
    }




    /**
     * 检测照片横竖
     */
    public static boolean isImgHorizontal(String fileString) {
        Bitmap bitmap = BitmapFactory.decodeFile(fileString);
        boolean temp = bitmap.getWidth() > bitmap.getHeight();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        return temp;
    }

    /**
     * 检测照片横竖
     */
    public static boolean isImgHorizontal(Bitmap bitmap,boolean shouldRecycle) {
        if(bitmap==null){
            return false;
        }
        boolean temp = bitmap.getWidth() > bitmap.getHeight();
        if (bitmap != null&&shouldRecycle) {
            bitmap.recycle();
            bitmap = null;
        }
        return temp;
    }
}
