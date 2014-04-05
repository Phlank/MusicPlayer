package com.google.colinmledbetter.musicplayer.model;

import java.io.File;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

public class Song {
	
	private Tag tag;
	
	public Song(String filepath) {
		try {
			AudioFile file = AudioFileIO.read(new File(filepath));
			tag = file.getTag();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	public String getSongTitle() {
		return tag.getFirst(FieldKey.TITLE);
	}
	
	public String getArtistTitle() {
		return tag.getFirst(FieldKey.ARTIST);
	}
	
	public String getAlbumTitle() {
		return tag.getFirst(FieldKey.ALBUM);
	}
	
	public String getAlbumArtistTitle() {
		return tag.getFirst(FieldKey.ALBUM_ARTIST);
	}

}
