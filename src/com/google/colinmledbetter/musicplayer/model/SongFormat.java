package com.google.colinmledbetter.musicplayer.model;

import org.jaudiotagger.audio.AudioHeader;

public enum SongFormat {

	MP3, M4A, WAV, AIFF, FLAC, OGG, OTHER;

	public static SongFormat extractFormatFromHeader(AudioHeader header) {
		switch (header.getFormat()) {
		case "WAV-RIFF 16 bits":
			return WAV;
		case "MPEG-1 Layer 3":
			return MP3;
		default:
			return OTHER;
		}
	}

}
