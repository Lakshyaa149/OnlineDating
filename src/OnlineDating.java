import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OnlineDating {

    public static  int currentId;

    HashMap<Integer, HashSet> matchedInApp;
    HashMap<Integer,User> usersInfo;
    List<User> maleUsers;
    List<User> femaleUsers;

    public  OnlineDating(){
        currentId=0;
        matchedInApp=new HashMap<>();
        usersInfo=new HashMap<>();
        this.maleUsers=new ArrayList<>();
        this.femaleUsers=new ArrayList<>();
    }


    public User createUser(Gender gender,int age,String interest,Double xLoc,Double yLoc){
        User user=new User(age,xLoc,yLoc,gender,new HashSet<>(),interest);
        usersInfo.put(user.userId,user);
        saveToGenderData(user);
        return user;
    }

    private void saveToGenderData(User user) {
        if(user.getGender().toString().equals("MALE")){
            maleUsers.add(user);
        }
        else{
            femaleUsers.add(user);
        }
    }

    public void likedUser(Integer userId,Integer likedUserId) {
        User user = usersInfo.get(userId);
        User likedUser = usersInfo.get(likedUserId);
        //put in match map
        if(!usersInfo.containsKey(userId) || !usersInfo.containsKey(likedUserId)){
            System.out.println("one of the User does not exist for these id's");
            return;
        }
        if(likedUser.set.contains(userId)){
            saveMatchForUsers(userId,likedUserId);
            saveMatchForUsers(likedUserId,userId);
        }
        user.set.add(likedUserId);
        System.out.println("user=" + user.userId + " likes user with id=" + likedUser.userId);
    }

    private void saveMatchForUsers(Integer userId, Integer likedUserId) {
        System.out.println("Match for user="+userId+" and user with id"+likedUserId);
        User user = usersInfo.get(userId);
        User likedUser = usersInfo.get(likedUserId);
        HashSet<Integer> set=new HashSet<>();
        if(!matchedInApp.containsKey(userId)){
            set.add(likedUserId);
            matchedInApp.put(userId,set);
        }
        else{
            set=matchedInApp.get(userId);
            set.add(likedUserId);
            matchedInApp.put(userId,set);
        }
    }

    public void showAllMatches(){
        matchedInApp.keySet().stream().forEach(user->showMatchesWithUser(user));
    }

    private void showMatchesWithUser(Integer userId) {
        HashSet<Integer> set=matchedInApp.get(userId);
        if(set.size()==0){
            System.out.println("there are no matches for user="+userId);
            return;
        }
        System.out.println("Matches of user="+userId);
        set.stream().forEach(user->System.out.println(usersInfo.get(user).userId));
    }

    public void deleteAccount(Integer userId){
        User user=usersInfo.get(userId);
        user.set.clear();
        matchedInApp.get(userId).stream().forEach(u->matchedInApp.get(u).remove(userId));
        matchedInApp.get(userId).clear();
        System.out.println("Deleting likes and matches for user="+userId);

    }

    public List<PotentialPoints> potentialMatchesForUser(Integer userId){
        User potentieUser=usersInfo.get(userId);
        List<PotentialData> potentialDatas=new ArrayList<>();
        List<PotentialPoints> potentialPoints=new ArrayList<>();
        if(potentieUser.getGender().toString().equals("MALE")) {
            List<User> femaleUsers = this.femaleUsers;
            femaleUsers.stream().forEach(femaleUser -> potentialDatas.add(createPotentialDataForUser(potentieUser, femaleUser)));
        }
        else{
            List<User> maleUsers=this.maleUsers;
            femaleUsers.stream().forEach(maleUser -> potentialDatas.add(createPotentialDataForUser(potentieUser, maleUser)));
        }
        for(PotentialData data:potentialDatas){
                double points=calculatePoints(data);
                PotentialPoints points1=new PotentialPoints();
                points1.points=points;
                points1.userId=data.user.userId;
                potentialPoints.add(points1);
            }
            potentialPoints.sort(new PotentialPoints());

        return potentialPoints;
    }
    private double calculatePoints(PotentialData data) {
        double points=0.0;
        points+=data.ageDiff;
        points+=data.distanceBetweenPotentieAndPotential;
        return points;
    }

    private PotentialData createPotentialDataForUser(User user,User otherUser) {
        PotentialData data=new PotentialData();
        data.user=otherUser;
        data.setAgeDiff(Math.abs(user.age-otherUser.age));
        double dist=calculateDistance(user.xLoc,user.yLoc,otherUser.xLoc,otherUser.yLoc);
        data.setDistanceBetweenPotentieAndPotential(dist);
        return data;
    }

    private double calculateDistance(double xLoc, double yLoc, double xLoc1, double yLoc1) {
        //List<PotentialData> potentialData=null;
       double value= Math.abs(xLoc1-xLoc);
        double value2= Math.abs(yLoc1-yLoc);
        double distanceCalulated=Math.sqrt((value*value)+(value2*value2));
        return distanceCalulated;
    }
}
