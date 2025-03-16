package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a music streaming service with a list of artists and a list of users.
 *
 * @param artists the list of artists
 * @param users   the list of users
 */
public record MusicStreaming(List<Artist> artists, List<User> users) {
    /**
     * Retrieves all the songs from all the artists on the platform.
     *
     * @return a list of all songs available on the platform
     */
    @StudentImplementationRequired
    public List<Song> getAllSongs() {
        // TODO H11.4.1
        return crash();
    }

    /**
     * Generates a stream of random songs from the platform.
     *
     * @return a stream of random songs
     */
    @StudentImplementationRequired
    public Stream<Song> generateRandomPlaylist() {
        // TODO H11.4.2
        return crash();
    }

    /**
     * Retrieves a random song from the platform's song list.
     *
     * @return a random song, or null if there are no songs available
     */
    @DoNotTouch
    public Song getRandomSong() {
        List<Song> allSongs = getAllSongs();
        if (allSongs.isEmpty()) {
            return null;
        }
        return allSongs.get((int) (Math.random() * allSongs.size()));
    }

    /**
     * Retrieves a list of songs that are longer than the specified duration.
     *
     * @param durationInSeconds the duration in seconds to compare the song lengths against
     * @return a list of songs that are longer than the specified duration
     */
    @StudentImplementationRequired
    public List<Song> getSongsLongerThan(int durationInSeconds) {
        // TODO H11.4.3
        return crash();
    }

    /**
     * Retrieves all unique genres available on the platform.
     *
     * @return a list of all unique genres available on the platform
     */
    @StudentImplementationRequired
    public List<Genre> getAllGenres() {
        // TODO H11.4.4
        return crash();
    }

    /**
     * Groups all albums by their genre.
     *
     * @return a map where the key is the genre and the value is a list of albums of that genre
     */
    @StudentImplementationRequired
    public Map<Genre, List<Album>> getAlbumsByGenre() {
        // TODO H11.4.5
        return crash();
    }

    /**
     * Calculates the global play counts for all songs by summing the play counts from all users.
     *
     * @return a list of entries where each entry is a song and its total play count, sorted by play count in descending order.
     * Songs with the same play count are ordered alphabetically by the song title.
     */
    @StudentImplementationRequired
    public List<Map.Entry<Song, Long>> getGlobalPlayCounts() {
        // TODO H11.4.6
        return crash();
    }

    /**
     * Retrieves a list of the top played songs formatted as strings.
     *
     * @return a list of strings representing the top 5 most played songs and their play counts in the format "[title] ([count] plays)"
     */
    @StudentImplementationRequired
    public List<String> getTopPlayedSongsList() {
        // TODO H11.4.6
        return crash();
    }

    /**
     * Retrieves the total playtime of an artist's songs based on global play counts.
     *
     * @param artist the artist whose playtime is to be calculated
     * @return the total playtime of the artist's songs in seconds
     */
    @StudentImplementationRequired
    public long getArtistPlaytime(Artist artist) {
        // TODO H11.4.7
        return crash();
    }

    /**
     * Retrieves the total playtimes for all artists.
     *
     * @return a map where the key is the artist and the value is their total playtime in seconds
     */
    @StudentImplementationRequired
    public Map<Artist, Long> getArtistPlaytimes() {
        // TODO H11.4.7
        return crash();
    }

    /**
     * Retrieves the most played artist based on the total playtime of their songs.
     *
     * @return the most played artist, or null if there are no artists
     */
    @StudentImplementationRequired
    public Artist getMostPlayedArtist() {
        // TODO H11.4.8
        return crash();
    }

    /**
     * Searches for songs that match the given predicate.
     *
     * @param predicate the predicate to filter songs
     * @return a list of songs that match the predicate
     */
    @StudentImplementationRequired
    public List<Song> searchSongs(Predicate<? super Song> predicate) {
        // TODO H11.4.9
        return crash();
    }

    /**
     * Adjusts the monthly subscription price for all users by a given percentage.
     *
     * @param percentage the percentage to adjust the price by (e.g., 0.10 for a 10% increase)
     */
    @StudentImplementationRequired
    public void adjustPrice(double percentage) {
        // TODO H11.4.10
        crash();
    }
}
