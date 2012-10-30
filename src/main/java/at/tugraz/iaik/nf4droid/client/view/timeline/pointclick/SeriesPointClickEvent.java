package at.tugraz.iaik.nf4droid.client.view.timeline.pointclick;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.gwt.event.shared.GwtEvent;

public class SeriesPointClickEvent extends
		GwtEvent<SeriesPointClickEventHandler> {

	 public static Type<SeriesPointClickEventHandler> TYPE = new Type<SeriesPointClickEventHandler>();

	    private final Long httpReqId;
	    private final ExposeType exposeType;
	    private final ExposeObscuring exposeObscuring;
	    private final ExposePoint exposePoint;
	    private final String exposedValue;

	    public SeriesPointClickEvent(Long httpReqId, ExposeType exposeType,
				ExposeObscuring exposeObscuring, ExposePoint exposePoint,
				String exposedValue) {
			super();
			this.httpReqId = httpReqId;
			this.exposeType = exposeType;
			this.exposeObscuring = exposeObscuring;
			this.exposePoint = exposePoint;
			this.exposedValue = exposedValue;
		}

		@Override
	    public Type<SeriesPointClickEventHandler> getAssociatedType() {
	        return TYPE;
	    }

	    @Override
	    protected void dispatch(SeriesPointClickEventHandler handler) {
	        handler.onPointClick(this);
	    }

		/**
		 * @return the httpReqId
		 */
		public Long getHttpReqId() {
			return httpReqId;
		}

		/**
		 * @return the exposeType
		 */
		public ExposeType getExposeType() {
			return exposeType;
		}

		/**
		 * @return the exposeObscuring
		 */
		public ExposeObscuring getExposeObscuring() {
			return exposeObscuring;
		}

		/**
		 * @return the exposePoint
		 */
		public ExposePoint getExposePoint() {
			return exposePoint;
		}

		/**
		 * @return the exposedValue
		 */
		public String getExposedValue() {
			return exposedValue;
		}
		
		

}
