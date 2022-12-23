package com.car.sale.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.car.sale.exception.ImageProcessError;

public class ImageUtil {
	public static byte[] compress(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}

		try {
			outputStream.close();
		} catch (IOException e) {
			throw new ImageProcessError("Image processing failed, please retry again!");
		}
		return outputStream.toByteArray();
	}

	public static byte[] decompress(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				byteArrayOutputStream.write(tmp, 0, count);
			}
			byteArrayOutputStream.close();
		} catch (Exception e) {
			throw new ImageProcessError("Image processing failed, please retry again!");
		}
		return byteArrayOutputStream.toByteArray();
	}
}
