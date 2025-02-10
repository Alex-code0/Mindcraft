package com.mindcraft.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mindcraft.GamePanel;
import com.mindcraft.KeyHandler;

public class Player extends Entity{

    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerSprites();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerSprites() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/playersprites/boy_right_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            if(keyHandler.upPressed == true) {
                direction = "up";
                y -= speed;
            }
    
            if(keyHandler.downPressed == true) {
                direction = "down";
                y += speed;
            }
    
            if(keyHandler.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
    
            if(keyHandler.rightPressed == true) {
                direction = "right";
                x += speed;
            }
    
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }   
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage playerSprite = null;
        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    playerSprite = up1;
                } 
                if(spriteNum == 2) {
                    playerSprite = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    playerSprite = down1;
                } 
                if(spriteNum == 2) {
                    playerSprite = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    playerSprite = left1;
                    
                } 
                if(spriteNum == 2) {
                    playerSprite = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    playerSprite = right1;
                }
                if(spriteNum == 2) {
                    playerSprite = right2;
                }
                break;
        }
        g2.drawImage(playerSprite, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
