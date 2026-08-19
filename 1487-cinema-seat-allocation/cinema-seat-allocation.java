class Solution{
    public int maxNumberOfFamilies(int n,int[][] reservedSeats){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int[] seat:reservedSeats){
            int row=seat[0];
            int s=seat[1];
            if(s==2||s==3||s==4||s==5) map.put(row,map.getOrDefault(row,0)|1);
            if(s==4||s==5||s==6||s==7) map.put(row,map.getOrDefault(row,0)|2);
            if(s==6||s==7||s==8||s==9) map.put(row,map.getOrDefault(row,0)|4);
        }
        long ans=(long)(n-map.size())*2;
        for(int mask:map.values()){
            if(mask==0) ans+=2;
            else if(mask==7) ans+=0;
            else if(mask==1||mask==4) ans+=1;
            else if(mask==2||mask==3||mask==6||mask==5) ans+=1;
        }
        return (int)ans;
    }
}