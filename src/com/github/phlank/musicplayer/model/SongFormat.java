package com.github.phlank.musicplayer.model;

/**
 * @author Colin Ledbetter
 */
public enum SongFormat {

	MP3, WAV, FLAC, OTHER;

	public static SongFormat headerFormatToEnumFormat(String headerFormat) {
		if (headerFormat.startsWith("WAV")) {
			return WAV;
		} else if (headerFormat.startsWith("MPEG-1 Layer 3")) {
			return MP3;
		} else if (headerFormat.startsWith("FLAC")) {
			return FLAC;
		} else {
			return OTHER;
		}
	}

}
