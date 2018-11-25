package File_format;

public class GPSObject {
	String Type;
	String AccuracyMeters;
	String AltitudeMeters;
	String CurrentLongitude;
	String CurrentLatitude;
	String RSSI;
	String Channel;
	String FirstSeen;
	String AuthMode;
	String SSID;
	String MAC;
	public GPSObject(String MAC,
			String SSID,
			String AuthMode,
			String FirstSeen,
			String Channel,
			String RSSI,
			String CurrentLatitude,
			String CurrentLongitude,
			String AltitudeMeters,
			String AccuracyMeters,
			String Type
			) {

		this.AccuracyMeters=AccuracyMeters;
		this.AltitudeMeters= AltitudeMeters;
		this.AuthMode= AuthMode;
		this.SSID=SSID;
		this.Channel=Channel;
		this.CurrentLatitude=CurrentLatitude;
		this.CurrentLongitude=CurrentLongitude;
		this.FirstSeen=FirstSeen;
		this.MAC=MAC;
		this.RSSI=RSSI;
		this.Type=Type;

	}

}