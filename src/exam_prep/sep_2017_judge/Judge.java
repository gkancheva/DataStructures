package exam_prep.sep_2017_judge;

import java.util.*;
import java.util.stream.Collectors;

public class Judge implements IJudge {

    private Map<Integer, Submission> submissions;
    private Set<Integer> contestIds;
    private Set<Integer> userIds;
    private Map<String, List<Integer>> contestsByType;
    private TreeMap<Integer, List<Submission>> submissionsByPoints;
    private Map<Integer,List<Submission>> submissionsByUserId;


    public Judge() {
        this.submissions = new TreeMap<>();
        this.contestIds = new TreeSet<>();
        this.userIds = new TreeSet<>();
        this.contestsByType = new HashMap<>();
        this.submissionsByPoints = new TreeMap<>();
        this.submissionsByUserId = new TreeMap<>();
    }

    public void addContest(int contestId) {
        this.contestIds.add(contestId);
    }

    public void addSubmission(Submission submission) {
        // – Adds a exam_prep.sep_2017_judge.Submission to the exam_prep.sep_2017_judge.Judge system.
        // The submission carries info about which Contest and User it belongs to.
        // Any attempt to add a exam_prep.sep_2017_judge.Submission with a non-existing User
        //or non-existing Contest should throw an InvalidOperationsException.
        if(!this.contestIds.contains(submission.getContestId()) || !this.userIds.contains(submission.getUserId())) {
            throw new IllegalArgumentException();
        }
        if(this.submissions.containsKey(submission.getId())) {
            return;
        }
        this.submissions.put(submission.getId(), submission);
        this.contestsByType.putIfAbsent(submission.getType().toString(), new LinkedList<>());
        this.contestsByType.get(submission.getType().toString()).add(submission.getContestId());

        this.submissionsByPoints.putIfAbsent(submission.getPoints(), new LinkedList<>());
        this.submissionsByPoints.get(submission.getPoints()).add(submission);

        this.submissionsByUserId.putIfAbsent(submission.getUserId(), new LinkedList<>());
        this.submissionsByUserId.get(submission.getUserId()).add(submission);
    }

    public void addUser(int userId) {
        this.userIds.add(userId);
    }

    public void deleteSubmission(int submissionId) {
        if(!this.submissions.containsKey(submissionId)){
            throw new IllegalArgumentException();
        }
        Submission s = this.submissions.remove(submissionId);
        this.submissionsByPoints.get(s.getPoints()).remove(s);
        this.submissionsByUserId.get(s.getUserId()).remove(s);
    }

    public Iterable<Submission> getSubmissions() {
        return new LinkedList<>(this.submissions.values());
    }

    public Iterable<Integer> getUsers() {
        return new LinkedList<>(this.userIds);
    }

    public Iterable<Integer> getContests() {
        return new LinkedList<>(this.contestIds);
    }

    public Iterable<Submission> submissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType) {
        List<Submission> result = new LinkedList<>();
        SortedMap<Integer, List<Submission>> subMap = submissionsByPoints
                .subMap(minPoints, maxPoints + 1);
        subMap.values()
                .forEach(x -> x.stream()
                        .filter(s -> s.getType().equals(submissionType))
                        .forEach(result::add));
        return result;
    }

    public Iterable<Integer> contestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId) {
        if(!this.userIds.contains(userId)) {
            throw new IllegalArgumentException();
        }
        return this.submissionsByUserId.get(userId).stream()
                .sorted(Comparator.comparing(Submission::getPoints, Comparator.reverseOrder())
                    .thenComparing(Submission::getId))
                .map(Submission::getContestId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Iterable<Submission> submissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId) {
        if(!this.contestIds.contains(contestId) ||
                !this.userIds.contains(userId) ||
                !this.submissionsByPoints.containsKey(points)) {
            throw new IllegalArgumentException();
        }
        return this.submissionsByPoints.get(points).stream()
                .filter(s -> s.getUserId() == userId && s.getContestId() == contestId)
                .collect(Collectors.toList());
    }

    public Iterable<Integer> contestsBySubmissionType(SubmissionType type) {
        return this.contestsByType.getOrDefault(type.toString(), new ArrayList<>());
    }
}
