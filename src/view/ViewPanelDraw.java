package view;

import controller.ButtonController;
import controller.ButtonsAnimationController;
import controller.CoinAnimation;
import controller.betAmountController;

import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static view.Config.*;

public class ViewPanelDraw extends JPanel{

    private BufferedImage background;

    private JButton betButton;
    public JButton getBetButton() {
        return betButton;
    }
    public JButton getBackButton() {
        return backButton;
    }

    private JButton backButton;
    private JLabel coinNumberLabel;
    private Timer coinTimer;
    private CoinAnimation coinAnimationListener;
    private JTextField betAmount;
    private JScrollPane dialogTextAreaScrollPane;


    private JLabel userNameLabel;
    private JLabel nextRonda;
    private JLabel betAmountLabel;
    private JLabel enterAmount;

    //1 min = 60000 ms proxima tirada
    private int temps = 60000;

    //private JTextArea dialogTextArea;

    //private BufferedImage dialog
    private BufferedImage [] stackAnimation;
    private BufferedImage [] moneyAnimation;
    private BufferedImage [] coinAnimation;
    private BufferedImage [] cartelAnimation;
    private BufferedImage ball;
    private BufferedImage avatar;

    //Mètodes per obtenir i canviar els frames de les animacions
    public int getFrameMoney() {
        return frameMoney;
    }
    public void setFrameMoney(int frameMoney) {
        this.frameMoney = frameMoney;
    }
    private int frameMoney;

    public int getFrameStack() {
        return frameStack;
    }
    public void setFrameStack(int frameStack) {
        this.frameStack = frameStack;
    }
    private int frameStack;

    public int getFrameCoin() {
        return frameCoin;
    }
    public void setFrameCoin(int frameCoin) {
        this.frameCoin = frameCoin;
    }
    private int frameCoin;

    public int getFrameCartel() {
        return frameCartel;
    }
    public void setFrameCartel(int frameCartel) {
        this.frameCoin = frameCartel;
    }
    private int frameCartel;

    public ViewPanelDraw(){

        this.setLayout(null);

        try{

            ImageIcon tempImage;

            //Background del JFrame
            background = ImageIO.read(new File(Config.RULETA_TABLE_IMAGE));
            avatar = ImageIO.read(new File (Config.USER_AVATAR_IMAGE));
            ball = ImageIO.read(new File (Config.BALL_ANIMATION_IMAGE));
            //dialog = ImageIO.read(new File (Config.DIALOG_BOX_IMAGE));

            //Back button
            backButton = new JButton();
            backButton.setVisible(true);
            tempImage = new ImageIcon(Config.BACK_BUTTON_IMAGE);
            backButton.setIcon(tempImage);
            backButton.setBorder(BorderFactory.createEmptyBorder());
            backButton.setBounds(50, 8, tempImage.getIconWidth(), tempImage.getIconHeight());
            backButton.setContentAreaFilled(false);
            backButton.setActionCommand(BACK_ACTIONCOMMAND);
            this.add(backButton);

            //Numero de monedes
            coinNumberLabel = new JLabel();
            coinNumberLabel.setFont(Config.font);
            coinNumberLabel.setFont(coinNumberLabel.getFont().deriveFont(60f));
            coinNumberLabel.setText("1000");
            coinNumberLabel.setBounds(280, 8, 1000, 50);
            coinNumberLabel.setBorder(BorderFactory.createEmptyBorder());
            coinNumberLabel.setVisible(true);
            this.add(coinNumberLabel, BorderLayout.SOUTH);

            //Usuari
            userNameLabel = new JLabel();
            userNameLabel.setFont(Config.font);
            userNameLabel.setFont(userNameLabel.getFont().deriveFont(40f));
            userNameLabel.setText("User");
            userNameLabel.setBounds(1320, 8, 500, 50);
            userNameLabel.setBorder(BorderFactory.createEmptyBorder());
            this.add(userNameLabel);

            //Bet button
            betButton = new JButton();
            betButton.setVisible(true);
            tempImage = new ImageIcon(BET_BUTTON_IMAGE);
            betButton.setIcon(tempImage);
            betButton.setBorder(BorderFactory.createEmptyBorder());
            betButton.setBounds(765, 895, tempImage.getIconWidth(), tempImage.getIconHeight());
            betButton.setContentAreaFilled(false);
            betButton.setActionCommand(BET_ACTIONCOMMAND);
            this.add(betButton, BorderLayout.SOUTH);

            //Timer de la partida
            //TODO: Listener que redueixi el timer.

            //Timer timerRonda = new Timer(50, rondaController);

            nextRonda = new JLabel();
            nextRonda.setFont(Config.font);
            nextRonda.setFont(nextRonda.getFont().deriveFont(35f));
            nextRonda.setForeground(Color.white);
            nextRonda.setText("Next round starts in " + Integer.toString(temps / 1000) + " seconds");
            nextRonda.setBounds(865, 385, 800, 50);
            this.add(nextRonda);


            enterAmount = new JLabel();
            enterAmount.setFont(Config.font);
            enterAmount.setFont(nextRonda.getFont().deriveFont(25f));
            enterAmount.setForeground(Color.white);
            enterAmount.setText("Enter amount ");
            enterAmount.setBounds(945, 915, 180, 50);
            this.add(enterAmount);

            //Bet Text Field
            betAmount = new JTextField();
            betAmount.setFont(Config.font);
            betAmount.setFont(nextRonda.getFont().deriveFont(30f));
            betAmount.setForeground(Color.white);
            betAmount.setBounds(1125, 915, 215, 50);
            betAmount.setOpaque(false);
            this.add(betAmount);

        }catch( IOException s){
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void paintComponent (Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(background, 0, 0, null);
        graphics2D.drawImage(cartelAnimation[frameCartel], 1000 - (cartelAnimation[frameCartel].getWidth() / 2), 75, null);
        graphics2D.drawImage(coinAnimation[frameCoin], 250 - (coinAnimation[frameCoin].getWidth() / 2), 10, null);
        graphics2D.drawImage(moneyAnimation[frameMoney], 380 - (moneyAnimation[frameMoney].getWidth() / 2), 85, null);
        graphics2D.drawImage(stackAnimation[frameStack], 1600 - (stackAnimation[frameStack].getWidth() / 2), 80, null);
        graphics2D.drawImage(avatar, 1255, 5, null);
        graphics2D.drawImage(ball, 515, 473, null);
        //graphics2D.drawImage(dialogBox, 10, 85, null);
    }

    //loads images and starts timer for animations
    public void startAnimations(ViewPanelDraw viewPanelDraw){

        //load images
        cartelAnimation = new BufferedImage[Config.CARTEL_ANIMATION_FRAME_NUMBER];
        coinAnimation = new BufferedImage[Config.COIN_ANIMATION_FRAME_NUMBER];
        moneyAnimation = new BufferedImage[Config.MONEY_ANIMATION_FRAME_NUMBER];
        stackAnimation = new BufferedImage[Config.STACK_ANIMATION_FRAME_NUMBER];



        try{

            //cartel animation
            for (int i = 0; i < Config.CARTEL_ANIMATION_FRAME_NUMBER; i++){
                cartelAnimation[i] = ImageIO.read(new File(Config.CARTEL_ANIMATION_IMAGE + (i + 1) + Config.PNG_EXTENSION));
                System.out.println("resources\\CARTEL_" + i +".png");
            }

            //coin animation
            for (int i = 0; i < Config.COIN_ANIMATION_FRAME_NUMBER; i++){
                coinAnimation[i] = ImageIO.read(new File(Config.COIN_ANIMATION_IMAGE + (i + 1) + Config.PNG_EXTENSION));
                System.out.println("resources\\coin_0" + i +".png");
            }

            //money animation
            for (int i = 0; i < Config.MONEY_ANIMATION_FRAME_NUMBER; i++){
                moneyAnimation[i] = ImageIO.read(new File(Config.MONEY_ANIMATION_IMAGE + (i + 1) + Config.PNG_EXTENSION));
                System.out.println("resources\\MONEY_" + i +".png");
            }

            //stack animation
            for (int i = 0; i < Config.STACK_ANIMATION_FRAME_NUMBER; i++){
                stackAnimation[i] = ImageIO.read(new File(Config.STACK_ANIMATION_IMAGE + (i + 1) + Config.PNG_EXTENSION));
                System.out.println("resources\\STACK_" + i +".png");
            }


            coinAnimationListener = new CoinAnimation(viewPanelDraw);
            coinTimer = new Timer(50 ,coinAnimationListener);
            coinTimer.start();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    //registers controllers
    public void registerControllers (ButtonController buttonController, ButtonsAnimationController buttonsAnimationController, betAmountController betAmountController){

        betButton.addActionListener(buttonController);
        backButton.addActionListener(buttonController);

        //////TEST////
        betButton.addMouseListener(buttonsAnimationController);
        backButton.addMouseListener(buttonsAnimationController);
        betAmount.addActionListener(betAmountController);
        System.out.println("Controllers registered");

    }
    /*
    //appends text to dialog box
    public void appendTextToDialogBox (String message){

        dialogTextArea.append(message);

    }
    */
    //change value JlabelSlider for current slider value
    public void updateBetLabelValue(){
        betAmountLabel.setText(betAmount.getText());
    }

}





