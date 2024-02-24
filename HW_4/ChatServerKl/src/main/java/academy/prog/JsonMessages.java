package academy.prog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class JsonMessages {
    private List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex , String address,int numberOfMessages) {
        for (int i = fromIndex; i < sourceList.size(); i++) {
            if (sourceList.get(i).getTo() == null
                    || sourceList.get(i).getTo().equals(address)
                    || sourceList.get(i).getFrom().equals(address)) {
                Message message = sourceList.get(i);
                message.setActualCount(numberOfMessages);
                list.add(message);
            }
        }
    }

    public JsonMessages(List<Message> sourceList) {
        Utils utils = new Utils();
        list =  sourceList.stream().filter(o -> utils.isUserPresent(o.getDate()))
                .distinct().collect(Collectors.toList());
    }

}
