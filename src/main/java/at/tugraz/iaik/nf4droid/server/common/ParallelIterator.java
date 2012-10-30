package at.tugraz.iaik.nf4droid.server.common;

import java.util.Iterator;


public class ParallelIterator<T1, T2> implements Iterator<ParallelIterator<T1,T2>.Pair<T1, T2>> {

	private final Iterator<T1> it1;
	private final Iterator<T2> it2;

	public ParallelIterator(Iterator<T1> it1, Iterator<T2> it2) {
		this.it1 = it1;
		this.it2 = it2;
	}

	@Override
	public boolean hasNext() {
		return it1.hasNext() && it2.hasNext();
	}

	@Override
	public ParallelIterator<T1,T2>.Pair<T1, T2> next() {
		return new Pair<T1, T2>(it1.next(), it2.next());
	}

	@Override
	public void remove() {
		it1.remove();
		it2.remove();
	}
	
	public class Pair<TT1, TT2> {
		private final TT1 v1;
		private final TT2 v2;

		public Pair(TT1 v1, TT2 v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		public TT1 getV1() {
			return v1;
		}

		public TT2 getV2() {
			return v2;
		}
	}
}
