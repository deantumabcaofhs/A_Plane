public class eBullet {
    int x,y,xSpeed,ySpeed,animtimer,act;

    public eBullet(int x, int y, int xs, int ys, int at, int act) {
        this.x=x;
        this.y=y;
        this.xSpeed=xs;
        this.ySpeed=ys;
        this.animtimer=at;
        this.act=act;
    }


    public void eMove() {
        x += xSpeed;
        y += ySpeed;
    }

    public boolean eCollision(int pX, int pY, int pW, int pH) {
        if (x + 20 / 2 >= pX && x - 20 / 2 <= pX + pW) {
            if (y + 20 / 2 >= pY && y - 20 / 2 <= pY + pH) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
}