class Solution{
    public String lexGreaterPermutation(String s,String target){
        int n=s.length();
        int[] cnt=new int[26];
        for(char c:s.toCharArray()) cnt[c-'a']++;
        char[] ans=new char[n];
        for(int i=0;i<n;i++) ans[i]=target.charAt(i);
        for(int i=0;i<n;i++){
            int x=target.charAt(i)-'a';
            if(cnt[x]==0){
                for(int p=i;p>=0;p--){
                    if(p<i) cnt[target.charAt(p)-'a']++;
                    int cur=target.charAt(p)-'a';
                    for(int c=cur+1;c<26;c++){
                        if(cnt[c]>0){
                            ans[p]=(char)('a'+c);
                            cnt[c]--;
                            int pos=p+1;
                            for(int d=0;d<26;d++){
                                while(cnt[d]>0){
                                    ans[pos++]=(char)('a'+d);
                                    cnt[d]--;
                                }
                            }
                            return new String(ans);
                        }
                    }
                }
                return "";
            }
            cnt[x]--;
        }
        for(int p=n-1;p>=0;p--){
            cnt[target.charAt(p)-'a']++;
            int cur=target.charAt(p)-'a';
            for(int c=cur+1;c<26;c++){
                if(cnt[c]>0){
                    ans[p]=(char)('a'+c);
                    cnt[c]--;
                    int pos=p+1;
                    for(int d=0;d<26;d++){
                        while(cnt[d]>0){
                            ans[pos++]=(char)('a'+d);
                            cnt[d]--;
                        }
                    }
                    return new String(ans);
                }
            }
        }
        return "";
    }
}