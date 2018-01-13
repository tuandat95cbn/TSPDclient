

public class Point2 {
	private int id;
	private double lat;
	private double lng;
	private boolean allowdrone;
	public int getID() {
		return id;
	}
	public void setID(int iD) {
		id = iD;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public boolean isAllowdrone() {
		return allowdrone;
	}
	public void setAllowdrone(boolean allowdrone) {
		this.allowdrone = allowdrone;
	}
	public Point2(int iD, double lat, double lng, boolean allowdrone) {
		super();
		id = iD;
		this.lat = lat;
		this.lng = lng;
		this.allowdrone = allowdrone;
	}
	public Point2() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Point2 [ID=" + id + ", lat=" + lat + ", lng=" + lng
				+ ", allowdrone=" + allowdrone + "]";
	}
	
	
}
