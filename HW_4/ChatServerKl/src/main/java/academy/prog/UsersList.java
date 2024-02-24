package academy.prog;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UsersList {
    private static Map<String, Date> onlineUsers = new HashMap<>();


    public synchronized void getRequestFromUsers(String userLogin) {
        onlineUsers.put(userLogin, new Date());
    }

    public synchronized List<String> getOnlineUsers() {
        List<String> listOfPresentUsers = new ArrayList<>();
        long currentTime = System.currentTimeMillis();

        for (Map.Entry<String, Date> entry : onlineUsers.entrySet()) {
            Date lastActivityTime = entry.getValue();
            long timeDifference = currentTime - lastActivityTime.getTime();
            long inactiveThreshold = TimeUnit.MILLISECONDS.toMillis(3000);

            if (timeDifference <= inactiveThreshold) {
                listOfPresentUsers.add(entry.getKey());
            } else {
                onlineUsers.remove(entry.getKey());
            }
        }

        return listOfPresentUsers;
    }

}
