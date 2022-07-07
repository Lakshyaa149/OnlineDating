public class OnlineDatingDriver {

    public OnlineDatingService getOnlineDatingService() {
        return onlineDatingService;
    }

    public void setOnlineDatingService(OnlineDatingService onlineDatingService) {
        this.onlineDatingService = onlineDatingService;
    }

    private  OnlineDatingService onlineDatingService=new OnlineDatingService();

    public OnlineDating getOnlineDating() {
        return onlineDating;
    }

    public void setOnlineDating(OnlineDating onlineDating) {
        this.onlineDating = onlineDating;
    }

    private OnlineDating onlineDating;

    public static void main(String[] args) {

        OnlineDatingDriver onlineDatingDriver=new OnlineDatingDriver();
        onlineDatingDriver.onlineDating=new OnlineDating();
      //   public User(int age, double xLoc, double yLoc, Gender gender, HashSet<Integer> set, String interest);
        User userA=onlineDatingDriver.getOnlineDating().createUser(Gender.MALE,20,"cricket",1.0,2.0);
        User userB=onlineDatingDriver.getOnlineDating().createUser(Gender.FEMALE,22,"basket",2.0,3.0);

        onlineDatingDriver.getOnlineDating().likedUser(userA.userId,userB.userId);
        onlineDatingDriver.getOnlineDating().likedUser(userB.userId,userA.userId);

        onlineDatingDriver.getOnlineDating().showAllMatches();

        onlineDatingDriver.getOnlineDating().deleteAccount(userA.userId);
        System.out.println("after deleting account");
        onlineDatingDriver.getOnlineDating().showAllMatches();

        System.out.println("creating other users");
        User userC=onlineDatingDriver.getOnlineDating().createUser(Gender.FEMALE,27,"carrom",3.0,4.0);
        User userD=onlineDatingDriver.getOnlineDating().createUser(Gender.FEMALE,26,"football",3.0,4.0);
        onlineDatingDriver.getOnlineDating().likedUser(userA.userId,userB.userId);
        onlineDatingDriver.getOnlineDating().likedUser(userB.userId,userA.userId);
        onlineDatingDriver.getOnlineDating().showAllMatches();
        System.out.println("potetial matches for user ");
        System.out.println(onlineDatingDriver.getOnlineDating().potentialMatchesForUser(userA.userId));








    }
}
