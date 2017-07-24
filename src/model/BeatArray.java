/* BeatArray.java
 *
 * Translates buttons pressed by the user into instrument beats
 */

package com.github.synthsgw.model;

import java.util.ArrayList;

public class BeatArray {
	public static BeatArray Beats = new BeatArray();

	private EnumMap<Instrument, boolean []> beats;

	public BeatArray() {
		beats = new EnumMap<>();
	}

	public void setBeat(Instrument inst, int beat, boolean value) {
		if(!beats.containsKey(inst)) {
			beats.add(new boolean[16]);
		}

		beats.get(inst)[beat] = value;
	}
}

