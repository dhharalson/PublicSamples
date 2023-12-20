
public class Song {
	private String title;
	private String artist;
	
	public Song () {
		title = null; // using null can sometimes invite confusion
		artist = null;
	}
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	public String getTitle() {
		if (title == null) { // checking for the actual null can help avoid confusion or errors
			return "";
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		if (artist == null) {
			return "";
		}
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof Song) {
			Song song = (Song)o;
			return title.equalsIgnoreCase(song.title);
		}
		else {
			return false;
		}
	}
	
}

