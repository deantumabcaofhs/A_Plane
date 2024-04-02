public class pBullet { //PLAYER
    int x, y, ySpeed, type, width, height;

    public pBullet(int x, int y, int ySpeed, int width, int height, int type) {
        this.x = x;
        this.y = y;
        this.ySpeed = ySpeed;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public boolean pMove() {
        if (type == 1) {
            if (x + width / 2 >= 0 && x - width / 2 <= 600 && y + height >= 0 && y - height <= 700) {
                y -= ySpeed;
            }else{
                return false;
            }
        }
        if (type == 2) {
            if (x + width / 2 >= 0 && x - width / 2 <= 600 && y + height >= 0 && y - height <= 700) {
                y -= ySpeed;
            }else{
                return false;
            }
        }
        return true;
    }

    public boolean pCollision(int eX, int eY, int eW, int eH) {
            if (x + width / 2 >= eX && x - width / 2 <= eX + eW) {
                return y + height / 2 >= eY && y - height / 2 <= eY + eH;
            } else {
                return false;
            }
        }
    }