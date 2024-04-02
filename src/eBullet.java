public class eBullet {
    int x,y,xSpeed,ySpeed,animtimer,act;
    int width = 20, height = 20;

    public eBullet(int x, int y, int xs, int ys, int at, int act) {
        this.x=x;
        this.y=y;
        this.xSpeed=xs;
        this.ySpeed=ys;
        this.animtimer=at;
        this.act=act;
    }


    public boolean eMove() {
        if (x + width / 2 >= 0 && x - width / 2 <= 600 && y + height >= 0 && y - height <= 700) {
            x += xSpeed;
            y += ySpeed;
        }else{
            return false;
        }
        return true;
    }

    public boolean eCollision(int pX, int pY, int pW, int pH) {
        if (x + width / 2 >= pX && x - height / 2 <= pX + pW) {
            return y + width / 2 >= pY && y - height / 2 <= pY + pH;
        }else{
            return false;
        }
    }
}