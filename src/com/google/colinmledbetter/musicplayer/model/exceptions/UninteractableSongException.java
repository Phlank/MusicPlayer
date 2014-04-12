package com.google.colinmledbetter.musicplayer.model.exceptions;

public class UninteractableSongException extends Exception {

	private static final long serialVersionUID = -2926990969424139935L;

	public UninteractableSongException() {
		super();
	}
	
	public UninteractableSongException(String message) {
		super(message);
	}

}
