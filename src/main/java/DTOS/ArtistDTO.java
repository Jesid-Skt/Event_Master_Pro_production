package DTOS;

public class ArtistDTO {
    private String artist_id;
    private String artist_name;
    private String genre;

    public ArtistDTO() {
    }

    public ArtistDTO(String artist_id, String artist_name, String genre) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.genre = genre;
    }

    public String getArtistId() {
        return artist_id;
    }

    public void setArtistId(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ArtistDTO{" +
                "artist_id='" + artist_id + '\'' +
                ", name='" + artist_name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
