import java.util.*;
class Solution{
    public List<String> braceExpansionII(String expression){
        Set<String> res=parse(expression,0,expression.length());
        List<String> ans=new ArrayList<>(res);
        Collections.sort(ans);
        return ans;
    }
    private Set<String> parse(String s,int l,int r){
        Set<String> result=new HashSet<>();
        Set<String> cur=new HashSet<>();
        cur.add("");
        int i=l;
        while(i<r){
            char c=s.charAt(i);
            if(c=='{'){
                int start=i+1,bal=1;
                i++;
                while(i<r&&bal>0){
                    if(s.charAt(i)=='{') bal++;
                    else if(s.charAt(i)=='}') bal--;
                    i++;
                }
                Set<String> part=parseGroup(s,start,i-1);
                cur=product(cur,part);
            }else if(c==','){
                result.addAll(cur);
                cur=new HashSet<>();
                cur.add("");
                i++;
            }else{
                Set<String> part=new HashSet<>();
                part.add(String.valueOf(c));
                cur=product(cur,part);
                i++;
            }
        }
        result.addAll(cur);
        return result;
    }
    private Set<String> parseGroup(String s,int l,int r){
        Set<String> result=new HashSet<>();
        Set<String> cur=new HashSet<>();
        cur.add("");
        int i=l;
        while(i<r){
            char c=s.charAt(i);
            if(c=='{'){
                int start=i+1,bal=1;
                i++;
                while(i<r&&bal>0){
                    if(s.charAt(i)=='{') bal++;
                    else if(s.charAt(i)=='}') bal--;
                    i++;
                }
                Set<String> part=parseGroup(s,start,i-1);
                cur=product(cur,part);
            }else if(c==','){
                result.addAll(cur);
                cur=new HashSet<>();
                cur.add("");
                i++;
            }else{
                Set<String> part=new HashSet<>();
                part.add(String.valueOf(c));
                cur=product(cur,part);
                i++;
            }
        }
        result.addAll(cur);
        return result;
    }
    private Set<String> product(Set<String> a,Set<String> b){
        Set<String> res=new HashSet<>();
        for(String x:a){
            for(String y:b){
                res.add(x+y);
            }
        }
        return res;
    }
}