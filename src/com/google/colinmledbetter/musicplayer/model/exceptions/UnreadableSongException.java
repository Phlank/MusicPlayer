package com.google.colinmledbetter.musicplayer.model.exceptions;

public class UnreadableSongException extends Exception {

	private static final long serialVersionUID = -2926990969424139935L;

	public UnreadableSongException() {
		super();
	}
	
	public UnreadableSongException(String message) {
		super(message);
	}

}
