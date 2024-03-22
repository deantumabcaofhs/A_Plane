//IMPORTS
import processing.core.PApplet; //Graphics
import processing.core.PImage; //Images
import ddf.minim.AudioPlayer; //Audio
import ddf.minim.Minim; //Audio
import java.util.ArrayList; //Array Lists

public class Main extends PApplet {
    //OBJECT CLASS
    pBullet pb; //Player bullets
    eBullet eb; //Enemy bullets
    BG bg;


    //VARIABLES
    int game, initialize, titleScreen, gameIntro, win, lose, play; //Game
    int bgStart,bgx, bgy; //Background
    float playerHealth, enemyHealth; //Health
    int px, py, pw, ph, pBulletTimer2, pBoostTimer, pBulletTimer, pBType, pc; //Player
    int ex, ey, exs, eys, ew, eh, ebxs, ebys,eFire, ec,eFireTimer, eAnimTimer,eBulletTimer, eBulletTimer2, eBulletTimer3b; //Enemy
    int rx, ry, lx, ly;
    float l, n, x, y;

    //HP Bars
    int enemyXHP, enemyYHP, enemyWHP, enemyHHP;
    int playerXHP, playerYHP, playerWHP, playerHHP;

    //Movement and collision
    boolean up, down, left, right, eCollision, pCollision;

    //Misc
    int timer;


    //IMAGES, AUDIO & LISTS
    PImage logo, playButton, playButton2,playButton3, player, player2, playerL1, playerL2, playerR1, playerR2, playerB1, playerB2, playerB3, playerB4, bgImage, playerBullet, playerBullet2, eb1 , eb2, eb3, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e1dmg, e2dmg, e3dmg, e4dmg, e5dmg, e6dmg, e7dmg, e8dmg, e9dmg, e10dmg, e11dmg, e12dmg;
    Minim audio;
    AudioPlayer title,playClick, playHover, bgm, warning, playerShoot, playerShoot2,playerHit, enemyHit, enemyShoot2, enemyPrepareShoot2, victory;
    ArrayList<pBullet> pBullet;
    ArrayList<eBullet> eBullet;
    ArrayList<BG> bgList;


    public void settings() {
        size(600, 700); //GRAPHICS WINDOW SIZE
    }

    public void setup() {
        surface.setTitle("A PLANE"); //App titlebar name
        surface.setResizable(true);
        titleScreen = 1;
        initialize = 0; //Starts game at launch

        //IMAGE INITIALIZING
        logo = loadImage("Images/A Plane Logo.png"); //Title screen logo
        playButton = loadImage("Images/PlayButton1.png"); //Play button
        playButton2 = loadImage("Images/PlayButton2.png"); //Play button hovered
        playButton3 = loadImage("Images/PlayButton3.png"); //Play button pressed
        bgImage= loadImage("Images/DefendersBG.png"); //Background scroll
        player = loadImage("Images/PlayerShip.png"); //Player ship neut, up, down
        player2 = loadImage("Images/PlayerShip2.png"); //Player ship damage
        playerBullet = loadImage("Images/PlayerBullet.png"); //Player projectile 1
        playerBullet2 = loadImage("Images/PlayerBullet2.png"); //Player projectile

        //Boss
        e1 = loadImage("Images/strikers1999chaosqueenb-0.png");
        e2 = loadImage("Images/strikers1999chaosqueenb-1.png");
        e3 = loadImage("Images/strikers1999chaosqueenb-2.png");
        e4 = loadImage("Images/strikers1999chaosqueenb-3.png");
        e5 = loadImage("Images/strikers1999chaosqueenb-4.png");
        e6 = loadImage("Images/strikers1999chaosqueenb-5.png");
        e7 = loadImage("Images/strikers1999chaosqueenb-6.png");
        e8 = loadImage("Images/strikers1999chaosqueenb-7.png");
        e9 = loadImage("Images/strikers1999chaosqueenb-8.png");
        e10 = loadImage("Images/strikers1999chaosqueenb-9.png");
        e11 = loadImage("Images/strikers1999chaosqueenb-10.png");
        e12 = loadImage("Images/strikers1999chaosqueenb-11.png");

        //Boss damage
        e1dmg = loadImage("Images/strikers1999chaosqueenb-12.png");
        e2dmg = loadImage("Images/strikers1999chaosqueenb-13.png");
        e3dmg = loadImage("Images/strikers1999chaosqueenb-14.png");
        e4dmg = loadImage("Images/strikers1999chaosqueenb-15.png");
        e5dmg = loadImage("Images/strikers1999chaosqueenb-16.png");
        e6dmg = loadImage("Images/strikers1999chaosqueenb-17.png");
        e7dmg = loadImage("Images/strikers1999chaosqueenb-18.png");
        e8dmg = loadImage("Images/strikers1999chaosqueenb-19.png");
        e9dmg = loadImage("Images/strikers1999chaosqueenb-20.png");
        e10dmg = loadImage("Images/strikers1999chaosqueenb-21.png");
        e11dmg = loadImage("Images/strikers1999chaosqueenb-22.png");
        e12dmg = loadImage("Images/strikers1999chaosqueenb-23.png");

        //Player
        playerL1 = loadImage("Images/PlayerShipL1.png");
        playerL2 = loadImage("Images/PlayerShipL2.png");
        playerR1 = loadImage("Images/PlayerShipR1.png");
        playerR2 = loadImage("Images/PlayerShipR2.png");
        playerB1 = loadImage("Images/PlayerShipB1.png");
        playerB2 = loadImage("Images/PlayerShipB2.png");
        playerB3 = loadImage("Images/PlayerShipB3.png");
        playerB4 = loadImage("Images/PlayerShipB4.png");
        eb1 = loadImage("Images/EnemyBullet1.png");
        eb2 = loadImage("Images/EnemyBullet24.png");
        eb3 = loadImage("Images/EnemyBullet3.png");


        //LIST INITIALIZING
        pBullet = new ArrayList<>();
        eBullet = new ArrayList<>();
        bgList = new ArrayList<>();


        //AUDIO INITIALIZING
        audio = new Minim(this);
        title = audio.loadFile("Audio/Strikers 1945 - III Black Wind (Rearranged Ver.).mp3");
        playClick = audio.loadFile("Audio/OptionSelect.mp3");
        playHover = audio.loadFile("Audio/SelectAnOption.mp3");
        bgm = audio.loadFile("Audio/BGM.mp3");
        warning = audio.loadFile("Audio/Warning.mp3");
        playerShoot = audio.loadFile("Audio/DefiniteShot.mp3");
        playerShoot2 = audio.loadFile("Audio/DefiniteMissileLaunch.mp3");
        playerHit = audio.loadFile("Audio/ExtraHitPoint.mp3");
        enemyHit = audio.loadFile("Audio/DefiniteHit.mp3");
        enemyPrepareShoot2 = audio.loadFile("Audio/MonsterEnergyReloadFull.mp3");
        enemyShoot2 = audio.loadFile("Audio/LaserMonsterAttack.mp3");
        victory = audio .loadFile("Audio/♪ Armed Wings (Fighters Select).mp3");
    }

    //GAME DRAWING & LOGIC
    public void draw() {
        background();

        if (titleScreen == 1) {
            titleScreen();
            if (initialize == 0) {
                initialize();
            }
        } else {
            if (gameIntro == 1) { //INTRO: PLAYER AND ENEMY FLY IN
                title.pause();
                gameIntro();
            }
        }

        //WIN/LOSE CHECK
        winLose();

        //GAME CONDITION
        if (game == 1) {
            if (win == 0 && lose == 0) {
                if (!bgm.isPlaying()) { //Loops BGM
                    bgm.play();
                }

                //WIN/LOSE CONDITION
                if (playerHealth <= 0) {
                    lose = 1;
                }
                if (enemyHealth <= 0) {
                    win = 1;
                }

                //ENEMY
                enemyHP();
                enemy();

                //PLAYER
                playerHP();
                player();

                pBullet();
                eBullet();
            }
        }
    }

    public void initialize(){
        //GAME
        titleScreen = 1;
        gameIntro = 1;
        timer = 0;

        //PLAYER
        px = 270;
        py = 800;
        pw = 64;
        ph = 64;

        pBType = 1;
        pBulletTimer = 0;

        //ENEMY
        ebxs = 2;
        ebys = 2;

        exs = 4;
        ex = 210;
        ey = -300;
        ew = 183;
        eh = 161;

        eBulletTimer = 0;
        eFireTimer = 0;

        //BACKGROUND
        bgx = 0;
        bgy = 0;

        //HEALTH
        enemyHealth = 500;
        playerHealth = 100;
        enemyWHP = 300;
        playerWHP = 164;

        initialize = 1; //Stops initialization
    }

    public void background(){ //BACKGROUND DRAWING
        if (win == 0 && lose == 0) {
            if(bgStart<1){
                bg = new BG(0, 0, 20);
                bgList.add(bg);
                bgStart++;
            }
            if (bg.bgCollision == 1) {
                bg = new BG(0, bg.s - 700, 20);
                bgList.add(bg);
                bg.bgCollision = 0;
            }
            for (BG bg : bgList) {
                if (bg.y <= height) {
                    image(bgImage, bg.x, bg.y);
                }
                bg.move();
                bg.detect();
            }
        }
    }
    public void titleScreen(){ //TITLE SCREEN
        if (!title.isPlaying()) { //Background MusicBGM.rewind();
            title.play();
        }
        image(logo,145,300);
        textSize(20);
        text("Coded with Java by Bengy and Dean.",120,690);
        if(play == 0) {
            if (mouseX >= 240 && mouseX <= 373) {
                if (mouseY >= 380 && mouseY <= 444) {
                    image(playButton2, 240, 380);
                    playHover.play();
                    if (mousePressed) {
                        playClick.play();
                        timer = 0;
                        play = 1;
                    }
                } else {
                    playHover.rewind();
                    image(playButton, 240, 380);
                }
            } else {
                playHover.rewind();
                image(playButton, 240, 380);
            }
        }

        if (play == 1) {
            if (timer != 25) {
                timer++;
                image(playButton3,240,380);
            }else{
                titleScreen = 0;
            }
        }
    }

    public void gameIntro(){ //GAME INTRO
        bgm.play(); //Background Music

        //ENEMY ANIMATION
        enemy();

        image(player, px, py); //Player drawing

        //PLAYER BOOSTER DRAWING
        if(pBoostTimer <4){
            image(playerB1,px,py);
            pBoostTimer++;
        }else if(pBoostTimer <8){
            image(playerB2,px,py);
            pBoostTimer++;
        }else if(pBoostTimer <12){
            image(playerB3,px,py);
            pBoostTimer++;
        }else if(pBoostTimer <16){
            image(playerB4,px,py);
            pBoostTimer =0;
        }

        if (py != 550) {
            py -= 5;
        } else if (ey-50<0) {
            warning.play();
            ey++;
        }else{
            if (timer != 25) {
                timer++;
            } else { //READY
                warning.pause();
                gameIntro = 0;
                game = 1;
            }
        }
    }

    public void enemy(){
        if (game == 1) {
            ex += exs;
            ey -= eys;

            if (ey <= 10 || ey + eh >= 250) {
                eys = -eys;
            }

            if (ex <= 25 || ex + ew >= width - 25) {
                exs = -exs;
            }
        }

        if (eAnimTimer < 2) {//e1 for 2 frames
            image(e1, ex, ey);
            if (pc == 1) {
                image(e1dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 4) {//e2
            image(e2, ex, ey);
            if (pc == 1) {
                image(e2dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 6) {//e3
            image(e3, ex, ey);
            if (pc == 1) {
                image(e3dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 8) {//4
            image(e4, ex, ey);
            if (pc == 1) {
                image(e4dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 10) {//5
            image(e5, ex, ey);
            if (pc == 1) {
                image(e5dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 12) {//6
            image(e6, ex, ey);
            if (pc == 1) {
                image(e6dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 14) {//7
            image(e7, ex, ey);
            if (pc == 1) {
                image(e7dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 16) {//8
            image(e8, ex, ey);
            if (pc == 1) {
                image(e8dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 18) {//9
            image(e9, ex, ey);
            if (pc == 1) {
                image(e9dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 20) {//10
            image(e10, ex, ey);
            if (pc == 1) {
                image(e10dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 22) {//11
            image(e11, ex, ey);
            if (pc == 1) {
                image(e11dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer++;
        } else if (eAnimTimer < 24) {//12
            image(e12, ex, ey);
            if (pc == 1) {
                image(e12dmg, ex, ey);
                pc = 0;
            }
            eAnimTimer = 0;
        }
    }

    public void player(){
        //PLAYER ACTIONS
        if (!up && !down && !left && !right) {
            if (ec == 1) {
                image(player2, px, py);
                ec = 0;
            } else {
                image(player, px, py);
            }
        }
        if (up) {
            if(py > 0) {
                py -= 6;
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }else{
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }
        }
        if (down) {
            if(py + ph < height) {
                py += 6;
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }else{
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }
        }
        if (left) {
            if (px > 0) {
                px -= 6;
                if (ec == 1) {
                    image(playerL2, px, py);
                    ec = 0;
                } else {
                    image(playerL1, px, py);
                }
            }else{
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }
        }
        if (right) {
            if (px + pw < width) {
                px += 6;
                if (ec == 1) {
                    image(playerR2, px, py);
                    ec = 0;
                } else {
                    image(playerR1, px, py);
                }
            }else{
                if (ec == 1) {
                    image(player2, px, py);
                    ec = 0;
                } else {
                    image(player, px, py);
                }
            }
        }

        //PLAYER BOOSTER DRAWING
        if (pBoostTimer < 4) {
            image(playerB1, px, py);
            pBoostTimer++;
        } else if (pBoostTimer < 8) {
            image(playerB2, px, py);
            pBoostTimer++;
        } else if (pBoostTimer < 12) {
            image(playerB3, px, py);
            pBoostTimer++;
        } else if (pBoostTimer < 16) {
            image(playerB4, px, py);
            pBoostTimer = 0;
        }
    }
    public void pBullet(){
        //PBULLET SWITCH
        if (keyPressed && key == '2' && pBType == 1) {
            pBType = 2;
        }
        if (keyPressed && key == '1' && pBType == 2) {
            pBType = 1;
        }

        //PBULLET TRIGGER
        if (mousePressed || keyPressed && key == ' ') {
            if (pBType == 1) {
                if (pBulletTimer == 0) {
                    shootPBullet();
                } else {
                    pBulletTimer--;
                }
            }
            if (pBType == 2) {
                if (pBulletTimer2 == 0) {
                    shootPBullet2();
                } else {
                    pBulletTimer2--;
                }
            }
        } else {
            pBulletTimer = 0;
            pBulletTimer2 = 0;
        }

        //DRAWS PLAYER BULLETS
        for (pBullet pb : pBullet) {
            fill(0, 0, 255);
            if (pb.t == 1) {
                if (pb.x + 8 / 2 >= 0 && pb.x - 8 / 2 <= width) {
                    if (pb.y + 14 >= 0 && pb.y - 14 <= height) {
                        image(playerBullet, pb.x - 4, pb.y + 2);

                    }
                }
            }
            if (pb.t == 2) {
                if (pb.x + 8 / 2 >= 0 && pb.x - 8 / 2 <= width) {
                    if (pb.y + 20 >= 0 && pb.y - 20 <= height) {
                        image(playerBullet2, pb.x - 4, pb.y + 2);

                    }
                }
            }
            pb.pMove();
            pCollision = pb.pCollision(ex, ey, ew, eh);
            if (pCollision) {
                pc = 1;
                enemyHit.play();
                enemyHit.rewind();
                enemyWHP = (int) ((enemyHealth / 500) * 300);
                if (pb.t == 1) {
                    enemyHealth--;
                }
                if (pb.t == 2) {
                    enemyHealth -= 10;
                }
                pb.y = -100;
            }
        }
    }
    public void eBullet(){
        //DRAWS ENEMY BULLETS
        for (eBullet eb : eBullet) {
            fill(255, 0, 0);
            if (eb.act == 1) {
                if (eb.x + 20 / 2 >= 0 && eb.x - 20 / 2 <= width) {
                    if (eb.y + 20 / 2 >= 0 && eb.y - 20 / 2 <= height) {
                        //ENEMY BULLET ANIMATION
                        if (eb.animtimer < 2) {
                            image(eb1, eb.x, eb.y);
                            eb.animtimer++;
                        } else if (eb.animtimer < 4) {
                            image(eb2, eb.x, eb.y);
                            eb.animtimer++;
                        } else if (eb.animtimer < 6) {
                            image(eb3, eb.x, eb.y);
                            eb.animtimer++;
                        } else if (eb.animtimer < 8) {
                            image(eb2, eb.x, eb.y);
                            eb.animtimer = 0;
                        }
                        eb.eMove();
                    } else {
                        eb.act = 0;
                    }
                } else {
                    eb.act = 0;
                }
            }
            eCollision = eb.eCollision(px, py, pw, ph);
            if (eb.act == 1) {
                if (eCollision) {
                    ec = 1;
                    playerHit.play();
                    playerHit.rewind();
                    playerHealth--;
                    playerWHP = (int) ((playerHealth / 100) * 164);
                    eb.act = 0;
                }
            }
        }

        //CHANGING ENEMY FIRING TYPES
        if (enemyHealth > 50) {
            if (eFireTimer != 500) {
                eFire = 1;
                eFireTimer++;
            }
            if (eFireTimer == 400) {
                enemyPrepareShoot2.play();
                enemyPrepareShoot2.rewind();
            }
            if (eFireTimer >= 500) {
                eFire = 2;
                eFireTimer++;
            }
            if (eFireTimer == 505) {
                enemyShoot2.play();
                enemyShoot2.rewind();
            }
            if (eFireTimer >= 1000) {
                eFire = 3;
            }
            if (eFireTimer >= 1500) {
                eFireTimer = 0;
            }
        } else {
            shootEBullet3();
        }


        if (eFire == 1) { //ENEMY FIRE 1
            if (eBulletTimer == 0) { //SPREAD FIRE
                shootEBullet();
            }
            if (eBulletTimer == 2) {
                ebxs *= -1;
                shootEBullet();
            } else {
                eBulletTimer--;
            }

        }
        if (eFire == 2) { //ENEMY FIRE 2
            if (eBulletTimer2 == 0) {
                shootEBullet2();
            }
            if (eBulletTimer == 2) {
                shootEBullet2();
            } else {
                eBulletTimer2--;
            }

        }

        if (eFire == 3) { //ENEMY FIRE 3
            if (eBulletTimer3b < 10) {
                eBulletTimer3b++;
                shootEBullet3();
            } else {
                if (eBulletTimer3b < 60) {
                    eBulletTimer3b++;
                } else {
                    eBulletTimer3b = 0;
                }
            }
        }
    }

    public void keyPressed(){ //PLAYER MOVEMENT
        if (key == 'w' || key == 'W') { //REMOVED VERTICAL MOVEMENT
            up = true;
        }
        if (key == 'a' || key == 'A') {
            left = true;
        }
        if (key == 's' || key == 'S') { //REMOVED VERTICAL MOVEMENT
            down = true;
        }
        if (key == 'd' || key == 'D') {
            right = true;
        }
    }

    public void keyReleased(){ //PLAYER MOVEMENT
        if (key == 'w' || key == 'W') {
            up=false;
        }
        if (key == 'a' || key == 'A') {
            left=false;
        }
        if (key == 's' || key == 'S') {
            down=false;
        }
        if (key == 'd' || key == 'D') {
            right=false;
        }
    }

    public void shootPBullet(){ //PLAYER FIRE
        playerShoot.play();
        playerShoot.rewind();
        pBullet pb = new pBullet(px + 16, py, 10, 8,20,1);
        pBullet.add(pb);
        pb = new pBullet(px + 48, py, 10, 8,20,1);
        pBullet.add(pb);
        pBulletTimer =5; //BULLET INTERVAL
    }
    public void shootPBullet2(){ //PLAYER FIRE
        playerShoot2.play();
        playerShoot2.rewind();
        pBullet pb = new pBullet(px + pw / 2, py + 1, 10, 8,14,2);
        pBullet.add(pb);
        pBulletTimer2 =20; //BULLET INTERVAL
    }


    public void shootEBullet(){ //ENEMY FIRE 1: SPREAD
        eBullet eb = new eBullet(ex + ew / 2 - 5, ey + eh - 10, ebxs, ebys,0,1);
        eBullet.add(eb);
        eBulletTimer=4;
    }

    public void shootEBullet2(){ //ENEMY FIRE 2: SPRAY N' PRAY
        eBullet eb = new eBullet(ex + ew / 2 - 5, ey + eh - 10, 0, 5,0,1);
        eBullet.add(eb);
        eBulletTimer2 =2;
    }

    public void shootEBullet3(){ //ENEMY FIRE 3: TRACKING
        rx = ex + (ew/2) - 5; //Middle Top Of Enemy
        ry = ey + eh - 10; //Bottom Of Enemy (rx,ry) = Middle Bottom of Enemy

        lx = px + (pw/2);
        ly = py + (ph/2); //(lx,ly) Middle of Player

        x = (lx - rx);
        y = (ly - ry);

        l = (y/x) * (x/45); // y/x -> y
        n = (x/y) * (y/45); // x/y -> x

        eBullet eb = new eBullet(rx, ry, (int) n, (int) l,0,1);
        eBullet.add(eb);
    }

    public void enemyHP(){
        //Enemy Properties Shown
        fill(255,0,0);
        stroke(0);
        enemyXHP = ex - 50;
        enemyYHP = ey;
        enemyHHP = 25;
        fill(0,0,0);
        stroke(0);
        rect(ex - 50,ey, ew + 100, 25);

        fill(255, 0, 0);
        rect(enemyXHP,enemyYHP,enemyWHP,enemyHHP);
        textSize(20);
        fill(255);
        text(Math.round(enemyHealth), enemyXHP + 3, enemyYHP + 21);

    }

    public void playerHP(){
        //Player Properties Shown
        playerXHP = px - 50;
        playerYHP = py + ph + 25;
        playerHHP = 25;

        fill(0,0,0);
        stroke(0);
        rect(px - 50,py + ph + 25,pw + 100,25);

        fill(0,0,255);
        stroke(0);
        rect(playerXHP,playerYHP,playerWHP,playerHHP);
        textSize(20);
        fill(255);
        text(Math.round(playerHealth), playerXHP + 3, playerYHP + 21);
    }

    //WIN/LOSE CONDITION
    public void winLose(){
        if (win == 1) {
            bgm.pause();
            if (!victory.isPlaying()) { //Loops Victory
                victory.play();
            }
            background(0);
            textSize(50);
            fill(0,255,0);
            text("VICTORY", 200, 370);
            textSize(25);
            fill(255);
            text("Health remaining: "+Math.round(playerHealth),175,410);
            if(playerHealth<10){
                textSize(15);
                fill(255,255,0);
                text("Close call!",380,430);
            }
        }if (lose == 1) {
            bgm.pause();
            background(0);
            textSize(50);
            fill(255,0,0);
            text("DEFEAT", 210, 370);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}