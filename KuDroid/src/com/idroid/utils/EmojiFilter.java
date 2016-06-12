package com.idroid.utils;

/**
 * 
 * @author yiyi
 * @date 2015年12月16日
 */
public class EmojiFilter {
	/**
	 * 只有表情的时候，判断会出错，提前加上该字符串，返回时去掉
	 */
	private final static String fixed = "6808dfcaf7bf6606:";

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return FALSE，包含图片
	 */
	public static boolean containsEmoji(String source) {
		return isConSpeCharacters(source);
	}

	private static boolean isConSpeCharacters(String string) {
		if (string.replaceAll("[\\p{P}]*[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
			// 不包含特殊字符
			return false;
		}
		return true;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		source += fixed;
		// if (!containsEmoji(source)) {
		// return source.replaceAll(fixed, "");// 如果不包含，直接返回
		// }

		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source.replaceAll(fixed, "");// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source.replaceAll(fixed, "");
			} else {
				return buf.toString().replaceAll(fixed, "");
			}
		}

	}
}
