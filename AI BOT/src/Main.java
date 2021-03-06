import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements MouseListener, KeyListener, Runnable
{
    Sword sword;
    Sword2 sword2;
    
    public static Graphics2D g;
    static public ScreenManager s;
    static public Window w;
    
    static SoundThread bsound = new SoundThread(); 
    
    static public Image open, play, help, about, exit, onePlayer, twoPlayer, background, knight1, knight2, attack1, attack2, defence1, defence2, server, client;
    
    static public boolean keyPlay = true, keyHelp = false, keyAbout=false, keyExit=false, keyOnePlayer = false, keyTwoPlayer=false, keyServer = false, keyClient = false;
    
    static public boolean About = false, Help = false, Exit = false, Menu = false, Open = true;
    
    static public boolean Play = false, oneP = false, twoP = false, Server = false, Client = false;
    
    int X1=98,X2=650;
    
    public static final DisplayMode modes1[]=
    {
        new DisplayMode(800,600,32,0),
    };
    
    public static void main(String args[])
    {
        Main main = new Main();
        main.run();
    }
    /**
     * This method initializes everything
     */
    public void init()
    {
        open = new ImageIcon("Files/Images/Menu/Open.png").getImage();
        about = new ImageIcon("Files/Images/Menu/About.png").getImage();
        help = new ImageIcon("Files/Images/Menu/Help.png").getImage();
        exit = new ImageIcon("Files/Images/Menu/Exit.png").getImage();
        play = new ImageIcon("Files/Images/Menu/Play.png").getImage();
        onePlayer = new ImageIcon("Files/Images/Menu/1p.png").getImage();
        twoPlayer = new ImageIcon("Files/Images/Menu/2p.png").getImage();
        background= new ImageIcon("Files/Images/Char/Background.png").getImage();
        knight1 = new ImageIcon("Files/Images/Char/Knight1.png").getImage();
        knight2 = new ImageIcon("Files/Images/Char/Knight2.png").getImage();
        attack1 = new ImageIcon("Files/Images/Char/Attack1.png").getImage();
        attack2 = new ImageIcon("Files/Images/Char/Attack2.png").getImage();
        defence1 = new ImageIcon("Files/Images/Char/Defence1.png").getImage();
        defence2 = new ImageIcon("Files/Images/Char/Defence2.png").getImage();
        client = new ImageIcon("Files/Images/Menu/Client.png").getImage();
        server = new ImageIcon("Files/Images/Menu/Server.png").getImage();
        s = new ScreenManager();
    }
    
    public void run() 
    {
        init();
        try
        {
            DisplayMode dm = s.findFirstCompatibleMode(modes1);
            s.setFullScreen(dm);
            w = s.getFullScreenWindow();
            w.addMouseListener(this);
            w.addKeyListener(this);
            Toolkit toolkit = Toolkit.getDefaultToolkit(); 
            Image image = toolkit.getImage("Files/Images/Ab.gif"); 
            Point hotSpot = new Point(0,0); 
            Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Ab");
            w.setCursor(cursor);
            while(Open)
            {
                Graphics2D g1 = s.getGraphics();
                MenuPaint.paintOpen(g1);
                s.update();
                g1.dispose();
                if(Menu)
                {
                    while(Menu)
                    {
                        Graphics2D g = s.getGraphics();
                        MenuPaint.paintMenu(g);
                        s.update();
                        g.dispose();
                        if(Exit)
                            System.exit(0);
                        if(Play)
                        {
                            while(Play)
                            {
                                g = s.getGraphics();
                                MenuPaint.paintPlayer(g);
                                s.update();
                                g.dispose();
                                /*while(oneP)
                                {
                                    g = s.getGraphics();
                                    paintPlay(g);
                                    s.update();
                                    g.dispose();
                                    try
                                    {
                                        Thread.sleep(500);
                                    }
                                    catch(Exception e){}
                                }*/
                                while(twoP)
                                {
                                    g = s.getGraphics();
                                    MenuPaint.paintConnection(g);
                                    s.update();
                                    g.dispose();
                                    while(Server)
                                    {
                                        {
                                            g = s.getGraphics();
                                            paintPlay(g);
                                            s.update();
                                            g.dispose();
                                            try
                                            {
                                                Thread.sleep(500);
                                            }
                                            catch(Exception e){}
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){}
        finally
        {
            s.restoreScreen();
        }
    }
    
    public void paintPlay(Graphics2D g1)
    {
        g1.drawImage(background,0,0,null);
        if(X1+50<=750)
        {
            X1+=50;
            X2-=50;
        }
        g1.drawImage(defence1,X1,250,55,53,null);
        g1.drawImage(defence2,X2,250,55,53,null);
    }
    
    public void keyPressed(KeyEvent e) 
    {
        Menu = true;
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            //System.exit(0);
            Play = false;
            oneP = false; 
            twoP = false; 
            Server = false; 
            Client = false;
            keyHelp = false; 
            keyAbout=false; 
            keyExit=false; 
            keyOnePlayer = false; 
            keyTwoPlayer=false; 
            keyServer = false; 
            keyClient = false;
            About = false;
            Help = false;
            keyPlay=true;
            Exit = false;
            Menu = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            if(keyPlay)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyPlay=false;
                keyExit=false;
                keyAbout=false;
                keyHelp = true;
            }
            else if(keyHelp)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyPlay=false;
                keyExit=false;
                keyHelp=false;
                keyAbout = true;
            }
            else if(keyAbout)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyPlay=false;
                keyHelp=false;
                keyAbout=false;
                keyExit = true;
            }
            else if(keyExit)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyHelp=false;
                keyExit=false;
                keyAbout=false;
                keyPlay = true;
            }
            if(keyOnePlayer)
            {
                sword2 = new Sword2();
                keyOnePlayer=false;
                keyTwoPlayer=true;
            }
            else if(keyTwoPlayer)
            {
                sword2 = new Sword2();
                keyTwoPlayer=false;
                keyOnePlayer=true;
            }
            if(keyServer)
            {
                sword2 = new Sword2();
                keyServer=false;
                keyClient=true;
            }
            else if(keyClient)
            {
                sword2 = new Sword2();
                keyClient=false;
                keyServer=true;
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            if(keyPlay)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyPlay=false;
                keyHelp=false;
                keyAbout=false;
                keyExit = true;
            }
            else if(keyHelp)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyAbout=false;
                keyExit=false;
                keyHelp=false;
                keyPlay = true;
            }
            else if(keyAbout)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyPlay=false;
                keyExit=false;
                keyAbout=false;
                keyHelp = true;
            }
            else if(keyExit)
            {
                sword2 = new Sword2();
                //keyOnePlayer=false;
                //keyTwoPlayer=false;
                keyHelp=false;
                keyExit=false;
                keyPlay=false;
                keyAbout = true;
            }
            if(keyOnePlayer)
            {
                sword2 = new Sword2();
                keyOnePlayer=false;
                keyTwoPlayer=true;
            }
            else if(keyTwoPlayer)
            {
                sword2 = new Sword2();
                keyTwoPlayer=false;
                keyOnePlayer=true;
            }
            if(keyServer)
            {
                sword2 = new Sword2();
                keyServer=false;
                keyClient=true;
            }
            else if(keyClient)
            {
                sword2 = new Sword2();
                keyClient=false;
                keyServer=true;
            }
            
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(keyServer)
            {
                sword = new Sword();
                Server = true;
            }
            else if(keyClient)
            {
                sword = new Sword();
                Client = true;
            }
            if(keyOnePlayer)
            {
                sword = new Sword();
                oneP=true;
            }
            else if(keyTwoPlayer)
            {
                sword = new Sword();
                keyServer = true;
                keyClient=false;
                twoP=true;
            }
            if(keyPlay)
            {
                sword = new Sword();
                keyOnePlayer = true;
                keyTwoPlayer = false;
                Play = true;
            }
            else if(keyHelp)
            {
                sword = new Sword();
                Help = true;
            }
            else if(keyAbout)
            {
                sword = new Sword();
                About = true;
            }
            else if(keyExit)
            {
                sword = new Sword();
                Exit=true;
            }
        }
    }
    
    public void mouseClicked(MouseEvent e) 
    {
        
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}