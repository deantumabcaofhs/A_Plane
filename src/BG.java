public class BG {
    int x,y,bgCollision,s;

    public BG(int x, int y,int s){
        this.x = x;
        this.y = y;
        this.s = s;
    }

    public void move(){
        y+=s;
    }
    public void detect(){
        if(y==s){
            bgCollision = 1;
        }
    }
}