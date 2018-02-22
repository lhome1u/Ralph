package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    protected static Texture texBlueBrick = new Texture("images/Brique1C.png");
    protected static Texture texGreenBrickA = new Texture("images/Brique2Ca.png");
    protected static Texture texGreenBrickB = new Texture("images/Brique2Cb.png");

    protected static Texture texBack = new Texture("images/Fond.png");
    protected static Texture texBall = new Texture("images/Bille.png");
    protected static Texture texBorder = new Texture("images/Contour.png");
    protected static Texture texRacket = new Texture("images/Barre.png");

    public TextureFactory(){
    }

    public static Texture getTexBlueBrick() {
        return texBlueBrick;
    }

    public static Texture getTexGreenBrickA() {
        return texGreenBrickA;
    }

    public static Texture getTexGreenBrickB() {
        return texGreenBrickB;
    }

    public static Texture getTexBack() { return texBack; }

    public static Texture getTexBall() { return texBall; }

    public static Texture getTexBorder() { return texBorder; }

    public static Texture getTexRacket() { return texRacket; }
}
