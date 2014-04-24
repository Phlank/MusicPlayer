package com.github.phlank.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;

public class MusicPlayerUtilsTest {

	@Test
	public void test5SecondsFormatsTo00005() {
		Assert.assertEquals("0:00:05",
				MusicPlayerUtils.secondsToFormattedString(5));
	}

	@Test
	public void test55SecondsFormatsTo00055() {
		Assert.assertEquals("0:00:55",
				MusicPlayerUtils.secondsToFormattedString(55));
	}

	@Test
	public void test355SecondsFormatsTo00555() {
		Assert.assertEquals("0:05:55",
				MusicPlayerUtils.secondsToFormattedString(355));
	}

	@Test
	public void test3355SecondsFormatsTo05555() {
		Assert.assertEquals("0:55:55",
				MusicPlayerUtils.secondsToFormattedString(3355));
	}

	@Test
	public void test21355SecondsFormatsTo55555() {
		Assert.assertEquals("5:55:55",
				MusicPlayerUtils.secondsToFormattedString(21355));
	}

}
