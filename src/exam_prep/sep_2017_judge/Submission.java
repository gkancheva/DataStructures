package exam_prep.sep_2017_judge;

public class Submission implements Comparable<Submission> {

    private int id;
    private int points;
    private SubmissionType type;
    private int contestId;
    private int userId;


    public Submission(int id, int points, SubmissionType type, int contestId, int userId) {
        this.id = id;
        this.points = points;
        this.type = type;
        this.contestId = contestId;
        this.userId = userId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public SubmissionType getType() {
        return this.type;
    }

    public void setType(SubmissionType type) {
        this.type = type;
    }

    public int getContestId() {
        return this.contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int compareTo(Submission o) {
        return Integer.compare(this.getId(), o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!Submission.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Submission submission = (Submission) obj;
        return this.id == submission.getId();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        return hash;
    }
}
