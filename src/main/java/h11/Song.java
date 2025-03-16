package h11;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a song with a title and duration.
 *
 * @param title             the title of the song
 * @param durationInSeconds the duration of the song in seconds
 */
public record Song(String title, int durationInSeconds) {
    /**
     * Checks if the song is longer than the specified duration.
     *
     * @param durationInSeconds the duration in seconds to compare against
     * @return true if the song's duration is greater than the specified duration, false otherwise
     */
    @StudentImplementationRequired
    public boolean isLongerThan(int durationInSeconds) {
        // TODO H11.1.1
        return crash();
    }
}
