package bachelors.fmi.uni.spacedefenders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlayerShip {

    Context context;
    int maxX;
    int maxY;

    int x;
    int y;


    Bitmap bitmap;

    public PlayerShip(Context context, int sizeX, int sizeY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.player);
        this.context = context;
        maxX = sizeX - bitmap.getWidth();
        maxY = sizeY;
        x = 0;
        y = sizeY - bitmap.getHeight();

    }
    public void update(int move){
        x += move;
        if(x < 0){
            x = 0;
        }else if(x > maxX){
            x = maxX;
        }
    }


}
