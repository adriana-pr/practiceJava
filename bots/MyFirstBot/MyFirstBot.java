import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

public class MyFirstBot extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new MyFirstBot().start();
    }

    // Constructor, which loads the bot config file
    MyFirstBot() {
        super(BotInfo.fromFile("MyFirstBot.json"));
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        setBodyColor(Color.fromString("#93384e")); 
        setTurretColor(Color.fromString("#930225")); 
        setRadarColor(Color.fromString("#930225")); 
        setBulletColor(Color.fromString("#ef0b42")); 
        setScanColor(Color.fromString("#e887ad")); 
        // Repeat while the bot is running
        while (isRunning()) {
            forward(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(1);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
}