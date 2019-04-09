/**
 *  The {@code Stopwatch} data type is for measuring
 *  the time that elapses between the start and end of a
 *  programming task (wall-clock time).
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class StopWatch
{
    /**
     * Initializes a new stopwatch.
     */
 private final long start = System.currentTimeMillis();
 
 /**
  * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
  *
  * @return elapsed CPU time (in seconds) since the stopwatch was created
  */
 public double elapsedTime()
 {
 long now = System.currentTimeMillis();
 return (now - start) / 1000.0;
 }
}