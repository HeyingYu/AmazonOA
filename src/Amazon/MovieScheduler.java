package Amazon;

public class MovieScheduler {

    public static int findEarliestFinishTime(int[] comedyReleaseTime, int[] comedyDuration, int[] dramaReleaseTime, int[] dramaDuration) {
        // Initialize the earliest finish times for comedy and drama to the latest possible integer value
        int earliestFinishComedy = Integer.MAX_VALUE;
        int earliestFinishDrama = Integer.MAX_VALUE;

        // Find the earliest finish time for comedy movies
        for (int i = 0; i < comedyReleaseTime.length; i++) {
            for (int j = 0; j < dramaReleaseTime.length; j++) {
                // Consider starting drama after comedy
                int startDramaAfterComedy = Math.max(comedyReleaseTime[i] + comedyDuration[i], dramaReleaseTime[j]);
                int finishDramaAfterComedy = startDramaAfterComedy + dramaDuration[j];
                // Update the earliest finish time for watching a drama after a comedy
                earliestFinishComedy = Math.min(earliestFinishComedy, finishDramaAfterComedy);

                // Consider starting comedy after drama
                int startComedyAfterDrama = Math.max(dramaReleaseTime[j] + dramaDuration[j], comedyReleaseTime[i]);
                int finishComedyAfterDrama = startComedyAfterDrama + comedyDuration[i];
                // Update the earliest finish time for watching a comedy after a drama
                earliestFinishDrama = Math.min(earliestFinishDrama, finishComedyAfterDrama);
            }
        }

        // The overall earliest finish time is the smaller of the two
        return Math.min(earliestFinishComedy, earliestFinishDrama);
    }

    public static void main(String[] args) {
        int[] comedyReleaseTime = {1, 4};
        int[] comedyDuration = {3, 2};
        int[] dramaReleaseTime = {5, 2};
        int[] dramaDuration = {2, 2};

        int earliestFinish = findEarliestFinishTime(comedyReleaseTime, comedyDuration, dramaReleaseTime, dramaDuration);
        System.out.println("The earliest finish time is: " + earliestFinish);
    }
}


