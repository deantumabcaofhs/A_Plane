public class pBullet { //PLAYER
    int x, y, ySpeed, t, w, h;

    public pBullet(int x, int y, int ys, int w, int h, int t) {
        this.x = x;
        this.y = y;
        this.ySpeed = ys;
        this.t = t;
        this.w = w;
        this.h = h;
    }

    public void pMove() {
        if (t == 1) {
            if (x + 8 / 2 >= 0 && x - 8 / 2 <= 600) {
                if (y + 14 >= 0 && y - 14 <= 700) {
                    y -= ySpeed;
                }
            }
        }
        if (t == 2) {
            if (x + 8 / 2 >= 0 && x - 8 / 2 <= 600) {
                if (y + 14 >= 0 && y - 14 <= 700) {
                    y -= ySpeed;

                }
            }
        }
    }

    public boolean pCollision(int eX, int eY, int eW, int eH) {
            if (x + w / 2 >= eX && x - w / 2 <= eX + eW) {
                if (y + h / 2 >= eY && y - h / 2 <= eY + eH) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }