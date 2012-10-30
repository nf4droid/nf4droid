package at.tugraz.iaik.nf4droid.client.view.timeline;

import java.util.ArrayList;
import java.util.List;

public class ColorPool {
	List<String> lineColorPool;
	List<String> flagColorPool;
	int linePosition = 0;
	int flagPosition = 0;

	public ColorPool() {
		super();
		lineColorPool = new ArrayList<String>();
		buildLineColors();
		flagColorPool = new ArrayList<String>();
		buildFlagColors();
	}

	private void buildFlagColors() {

		flagColorPool.add("#d92405");
		flagColorPool.add("#eac124");
		flagColorPool.add("#20acf3");
		flagColorPool.add("#0dc600");
		flagColorPool.add("#990066");
		flagColorPool.add("#60bdaf");
	}

	private void buildLineColors() {

		// by google
		lineColorPool.add("#3366cc");
		lineColorPool.add("#109618");
		lineColorPool.add("#990099");
		lineColorPool.add("#ff9900");
		lineColorPool.add("#dc3912");
	}

	public String getNextFlagColor() {
		String color = flagColorPool.get(flagPosition % flagColorPool.size());
		flagPosition++;
		return color;
	}

	public String getNextLineColor() {
		String color = lineColorPool.get(linePosition % lineColorPool.size());
		linePosition++;
		return color;
	}

	public void reset() {
		flagPosition = 0;
		linePosition = 0;
	}
}
