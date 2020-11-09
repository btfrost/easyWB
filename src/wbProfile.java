public class wbProfile {
    private final String type;
    private final String tailNumber;
    private final String emptyWeight;
    private final String emptyArm;
    private final String emptyMoment;

    public wbProfile() {
        type = null;
        tailNumber = null;
        emptyWeight = null;
        emptyArm = null;
        emptyMoment = null;
    }

    public wbProfile(String t, String s, String ew, String ea, String em) {
        type = t;
        tailNumber = s;
        emptyWeight = ew;
        emptyArm = ea;
        emptyMoment = em;
    }

    public String getType() { return type; }
    public String getTailNumber() { return tailNumber; }
    public String getEmptyWeight() { return emptyWeight; }
    public String getEmptyArm() { return emptyArm; }
    public String getEmptyMoment() { return emptyMoment; }
}
