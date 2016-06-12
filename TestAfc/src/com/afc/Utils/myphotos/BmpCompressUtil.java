package com.afc.Utils.myphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BmpCompressUtil {
	
	/**
	 * 尺寸压缩
	 * 压缩至宽高1000*10000以下
	 */
	/*public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000)
					&& (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(
						new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}
	
	*//**
	 * 质量压缩
	 *  将给定图片压缩至固定容量以下
	 *//*
	public static Bitmap compressImage(Bitmap image,int maxSize) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length/1024 > maxSize) { // 循环判断如果压缩后图片是否大于指定值,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
		}
		
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}*/
	
	
	/**
	 * 获取本地图片并压缩至指定宽高
	 * @param photoPath
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromFile(String photoPath,
	        int reqWidth, int reqHeight) {
	 
	    // 首先设置 inJustDecodeBounds=true 来获取图片尺寸
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    options.inPurgeable = true;
        options.inInputShareable = true;
	    BitmapFactory.decodeFile(photoPath,options);
	 
	    // 计算 inSampleSize 的值
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
	 
	    // 根据计算出的 inSampleSize 来解码图片生成Bitmap
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(photoPath, options);
	}
	/**
	 * 计算压缩比例
	 */
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // 原始图片的宽高
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;
 
    if (height > reqHeight || width > reqWidth) {
 
        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // 在保证解析出的bitmap宽高分别大于目标尺寸宽高的前提下，取可能的inSampleSize的最大值
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }
 
    return inSampleSize;
}
	
	
	
}
