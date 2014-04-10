package com.google.colinmledbetter.musicplayer.model.exceptions;

public class CorruptSongException extends Exception {

	private static final long serialVersionUID = 2167029671552467122L;

	public CorruptSongException() {
		super();
	}

	public CorruptSongException(String message) {
		super(message);
	}

}
