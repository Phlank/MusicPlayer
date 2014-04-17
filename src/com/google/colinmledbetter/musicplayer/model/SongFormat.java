package com.google.colinmledbetter.musicplayer.model;

import org.jaudiotagger.audio.AudioHeader;

public enum SongFormat {

	MP3, WAV, FLAC, OTHER;

	public static SongFormat extractFormatFromHeader(AudioHeader header) {
		System.out.println(header.getFormat());
		if (header.getFormat().startsWith("WAV")) {
			return WAV;
		} else if (header.getFormat().startsWith("MPEG-1 Layer 3")) {
			return MP3;
		} else if (header.getFormat().startsWith("FLAC")) {
			return FLAC;
		} else {
			return OTHER;
		}
	}

}
