package Amazon;

import java.util.*;

public class EmployeeSkills {
    public static int teams(List<Integer> skill, int size, int diff) {
        Collections.sort(skill);
        int i=0, f=size-1, j=i+f, count=0;
        while(j<skill.size()){
            if((skill.get(j)-skill.get(i))<=diff){
                count++;
                i+=f;
                j+=f;
            }
            i++;
            j++;
        }
        return count;
    }
    public static void main(String[] args) {
        List<Integer> priorities = Arrays.asList(9, 3, 5, 7, 1);
        int result = teams(priorities, 2, 1);
        System.out.println(result);
    }
}

