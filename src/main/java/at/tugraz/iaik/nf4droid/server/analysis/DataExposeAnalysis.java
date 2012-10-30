package at.tugraz.iaik.nf4droid.server.analysis;

import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;

public interface DataExposeAnalysis {
	public void analyseDataExpose(final TrafficCapture trafficCapture);
}
