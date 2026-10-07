import java.util.*;
class Solution{
    public List<String> removeInvalidParentheses(String s){
        int l=0,r=0;
        for(char c:s.toCharArray()){
            if(c=='(') l++;
            else if(c==')'){
                if(l>0) l--;
                else r++;
            }
        }
        Set<String> set=new HashSet<>();
        dfs(s,0,l,r,0,new StringBuilder(),set);
        return new ArrayList<>(set);
    }
    private void dfs(String s,int index,int lremove,int rremove,int open,StringBuilder path,Set<String> set){
        if(index==s.length()){
            if(lremove==0&&rremove==0&&open==0) set.add(path.toString());
            return;
        }
        char c=s.charAt(index);
        if(c=='('&&lremove>0){
            dfs(s,index+1,lremove-1,rremove,open,path,set);
        }
        if(c==')'&&rremove>0){
            dfs(s,index+1,lremove,rremove-1,open,path,set);
        }
        path.append(c);
        if(c!='('&&c!=')'){
            dfs(s,index+1,lremove,rremove,open,path,set);
        }else if(c=='('){
            dfs(s,index+1,lremove,rremove,open+1,path,set);
        }else if(open>0){
            dfs(s,index+1,lremove,rremove,open-1,path,set);
        }
        path.deleteCharAt(path.length()-1);
    }
}